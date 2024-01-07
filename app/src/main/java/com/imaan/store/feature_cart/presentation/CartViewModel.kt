package com.imaan.store.feature_cart.presentation

import androidx.lifecycle.ViewModel
import com.imaan.store.feature_cart.domain.model.TotalModel
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel(){
    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    fun increaseQuantity(item: CartItemModel){
        val updatedItems = _state.value.items.toMutableList()
        val index = updatedItems.indexOfFirst { it.id == item.id }
        if (index != -1) {
            updatedItems[index] = item.copy(quantity = item.quantity + 1)
            updateTotals(updatedItems)
        }
    }
    
    fun decreaseQuantity(item: CartItemModel){
        val updatedItems = _state.value.items.toMutableList()
        val index = updatedItems.indexOfFirst { it.id == item.id }
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
        val deliveryCharge = 40.0
        val subTotal = updatedItems.sumOf { it.totalPrice.toDouble() }
        val grandTotal = deliveryCharge + subTotal
        val updatedTotals = listOf(
            TotalModel(label = "Delivery", amount = deliveryCharge),
            TotalModel(label = "Subtotal", amount = subTotal),
            TotalModel(label = "Grand Total", amount = grandTotal)
        )
        _state.value = _state.value.copy(items = updatedItems, totals = updatedTotals)
    }
    
}


data class CartScreenState(
    val items: List<CartItemModel> = getDummyCartItems(2),
    val totals: List<TotalModel> = listOf(
        TotalModel(label = "Delivery",amount = 40.0),
        TotalModel(label = "Subtotal", amount = items.sumOf { it.totalPrice.toDouble() }),
        TotalModel(label = "Grand Total", amount = (40.0 + items.sumOf { it.totalPrice.toDouble() }))
    )
)