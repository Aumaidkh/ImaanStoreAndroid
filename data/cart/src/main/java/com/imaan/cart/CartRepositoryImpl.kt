package com.imaan.cart

import com.imaan.cart.CartDatasource.cartItems
import com.imaan.products.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class CartRepositoryImpl @Inject constructor(): ICartRepository {
    private val _cartItemsFlow = MutableStateFlow(emptyList<CartItemModel>())
    override val cartItemsFlow: StateFlow<List<CartItemModel>>
        get() = _cartItemsFlow.asStateFlow()

    override suspend fun fetchCartItems(): List<CartItemModel> {
        updateCartItemsWith(cartItems)
       return cartItems
    }

    override suspend fun addProductToCart(productModel: ProductModel): Boolean {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex == -1){
            cartItems.add(
                CartItemModel(
                    productModel
                )
            )
            updateCartItemsWith(cartItems)
            return true
        }
        return false
    }

    override suspend fun removeProductFromCart(productModel: ProductModel) {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex != -1){
            cartItems.remove(cartItems[cartItemIndex])
            updateCartItemsWith(cartItems)
        }
    }

    override suspend fun increaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel> {
        val updatedItems = cartItems
        val index = updatedItems.indexOfFirst { it.productModel.id == cartItemModel.productModel.id }
        if (index != -1) {
            val item = cartItems[index]
            updatedItems[index] = item.copy(quantity = item.quantity + 1)
        }
        cartItems = updatedItems
        updateCartItemsWith(cartItems)
        return cartItems
    }

    override suspend fun decreaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel> {
        val updatedItems = cartItems
        val index = updatedItems.indexOfFirst { it.productModel.id == cartItemModel.productModel.id }
        if (index != -1) {
            val item = cartItems[index]
            if (item.quantity > 1){
                updatedItems[index] = item.copy(quantity = item.quantity - 1)
            } else {
                updatedItems.removeAt(index)
            }
        }
        cartItems = updatedItems
        updateCartItemsWith(cartItems)
        return cartItems
    }

    private fun updateCartItemsWith(newItems: List<CartItemModel>){
        _cartItemsFlow.value = newItems
    }
}