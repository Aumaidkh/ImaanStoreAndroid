package com.imaan.cart

import com.imaan.products.ProductModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ICartRepository {

    val cartItemsFlow: StateFlow<List<CartItemModel>>
    suspend fun fetchCartItems(): List<CartItemModel>

    suspend fun addProductToCart(productModel: ProductModel): Boolean

    suspend fun removeProductFromCart(productModel: ProductModel)

    suspend fun increaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>
    suspend fun decreaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>
}