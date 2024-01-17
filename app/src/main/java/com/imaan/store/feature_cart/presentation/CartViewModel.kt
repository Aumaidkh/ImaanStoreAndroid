package com.imaan.store.feature_cart.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.OrderRepository
import com.imaan.store.core.domain.model.Amount
import com.imaan.store.core.domain.model.OrderModel
import com.imaan.store.core.domain.model.plus
import com.imaan.store.core.domain.model.sumOfAmounts
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_cart.domain.model.TotalModel
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CartViewModel"
@HiltViewModel
class CartViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel(){
    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val eventFlow get() = _event.receiveAsFlow()
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
                element = UiEvent.Success(null)
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


data class CartScreenState(
    val items: List<CartItemModel> = dummyCart,
    val totalModel: TotalModel = TotalModel(
        deliveryCharges = Amount(40.0),
        subtotal = items.sumOfAmounts { it.totalPrice },
        discount = Amount(40.00),
        grandTotal = ((40.0 - 20.0) plus items.sumOfAmounts { it.totalPrice })
    )
)
