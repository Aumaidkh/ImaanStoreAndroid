package com.imaan.cart


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.Amount
import com.imaan.common.model.ID
import com.imaan.common.model.sumOfAmounts
import com.imaan.order.IOrderRepository
import com.imaan.order.OrderModel
import com.imaan.products.ProductModel
import com.imaan.total.TotalModel
import com.imaan.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CartViewModel"
@HiltViewModel
class CartViewModel @Inject constructor(
    private val orderRepository: IOrderRepository,
    private val cartRepository: ICartRepository
): ViewModel(){
    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<CartScreenEvent<String>>()
    val eventFlow get() = _event.receiveAsFlow()

    init {
        viewModelScope.launch {
            cartRepository.fetchCartItemsFlow(ID("")).onEach {result ->
                when(result){
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                items = result.data,
                                totalModel = calculateTotalsFor(result.data)
                            )
                        }
                    }
                    is Result.Error -> {
                        Log.d(
                            TAG,
                            "loadCart: Error: ${result.throwable.message}"
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    private fun calculateTotalsFor(items: List<CartItemModel>): TotalModel {
        val subTotal = items.sumOfAmounts { it.totalPrice }
        val discount = items.sumOfAmounts { Amount((it.productModel as? ProductModel)?.discount?.value?.toDouble() ?: 0.0) }
        val grandTotal = _state.value.totalModel.deliveryCharges plus subTotal minus discount
        return TotalModel(
            grandTotal = grandTotal,
            subtotal = subTotal,
            deliveryCharges = Amount(40.0),
            discount = discount
        )
    }

    fun increaseQuantity(item: CartItemModel){
        viewModelScope.launch {
            cartRepository.updateCartItemQuantity(
                quantity = ICartRepository.Quantity.Increase,
                itemModel = item
            ).collect{ result ->
                when(result){
                    is Result.Success -> {
                        Log.d(
                            TAG,
                            "increaseQuantity: Increased Quantity Successfull"
                        )
                        _state.update {
                            it.copy(
                                totalModel = calculateTotalsFor(_state.value.items)
                            )
                        }
                    }
                    is Result.Error -> {
                        Log.d(
                            TAG,
                            "increaseQuantity: Error Increasing Quantity"
                        )
                    }
                }
            }
        }
    }

    fun decreaseQuantity(item: CartItemModel){
        viewModelScope.launch {
            cartRepository.updateCartItemQuantity(
                quantity = ICartRepository.Quantity.Decrease,
                itemModel = item
            ).collect{ result ->
                when(result){
                    is Result.Success -> {
                        Log.d(
                            TAG,
                            "decreaseQuantity: Decreased Quantity Successfull"
                        )
                        _state.update {
                            it.copy(
                                totalModel = calculateTotalsFor(_state.value.items)
                            )
                        }
                    }
                    is Result.Error -> {
                        Log.d(
                            TAG,
                            "decreaseQuantity: Error Decreasing Quantity"
                        )
                    }
                }
            }
        }
    }

    private fun updateTotals(updatedItems: List<CartItemModel>) {
        val subTotal = updatedItems.sumOfAmounts { it.totalPrice }
        val grandTotal = _state.value.totalModel.deliveryCharges plus subTotal
        _state.value = _state.value.copy(items = updatedItems)
        _state.update {
            it.copy(
                items = updatedItems,
                totalModel = _state.value.totalModel.copy(
                    subtotal = subTotal,
                    grandTotal = grandTotal
                )
            )
        }
    }

    fun removeItemFromCart(item: CartItemModel){
        viewModelScope.launch {
            cartRepository.removeItemFromCart(item).onEach { result ->
                when(result){
                    is Result.Success -> {
                        _event.send(CartScreenEvent.Success("Item Removed from cart"))
                    }
                    is Result.Error -> {
                        _event.send(CartScreenEvent.Error(result.throwable))
                    }
                }
            }.launchIn(this)
        }
    }

    fun proceedToCheckOut(){
        // Save info in Order Model
        viewModelScope.launch {
            saveInfoInOrderModel()
            _event.send(
                element = CartScreenEvent.Success(null)
            )
        }
    }

    private suspend fun saveInfoInOrderModel(){
        orderRepository.updateOrder(
            orderModel = OrderModel(
                cartItems = _state.value.items,
                deliveryCharges = _state.value.totalModel.deliveryCharges,
                discount = _state.value.totalModel.subtotal
            )
        )
    }

}