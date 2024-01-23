package com.imaan.cart


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.Amount
import com.imaan.common.model.sumOfAmounts
import com.imaan.order.IOrderRepository
import com.imaan.order.OrderModel
import com.imaan.total.TotalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    cartRepository: ICartRepository
): ViewModel(){
    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<CartScreenEvent<String>>()
    val eventFlow get() = _event.receiveAsFlow()

    init {
        cartRepository.cartItemsFlow.onEach { items ->
            _state.update {
                it.copy(
                    items = items,
                    totalModel = calculateTotalsFor(items)
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun calculateTotalsFor(items: List<CartItemModel>): TotalModel {
        val subTotal = items.sumOfAmounts { it.totalPrice }
        val grandTotal = _state.value.totalModel.deliveryCharges plus subTotal
        return TotalModel(
            grandTotal = grandTotal,
            subtotal = subTotal,
            deliveryCharges = Amount.ZERO,
            discount = Amount.ZERO
        )
    }

    fun increaseQuantity(item: CartItemModel){
        val updatedItems = _state.value.items.toMutableList()
        val index = updatedItems.indexOfFirst { it.productModel.id == item.productModel.id }
        if (index != -1) {
            updatedItems[index] = item.copy(quantity = item.quantity + 1)
            updateTotals(updatedItems)
        }
    }

    fun decreaseQuantity(item: CartItemModel){
        val updatedItems = _state.value.items.toMutableList()
        val index = updatedItems.indexOfFirst { it.productModel.id == item.productModel.id }
        if (index != -1) {
            if (item.quantity > 1){
                updatedItems[index] = item.copy(quantity = item.quantity - 1)
                updateTotals(updatedItems)
            } else {
                updatedItems.removeAt(index)
                updateTotals(updatedItems)
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
        _state.update {
            it.copy(
                items = _state.value.items - item
            )
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
                subtotalAmount = _state.value.totalModel.subtotal,
                discount = _state.value.totalModel.subtotal,
                totalAmount = _state.value.totalModel.grandTotal
            )
        )
    }

}