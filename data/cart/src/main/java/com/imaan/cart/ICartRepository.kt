package com.imaan.cart

import com.imaan.products.ProductModel
import com.imaan.products.model.IProductModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ICartRepository {

    val cartItemsFlow: StateFlow<List<CartItemModel>>
    suspend fun fetchCartItems(): List<CartItemModel>

    suspend fun addProductToCart(productModel: IProductModel): Boolean

    suspend fun removeProductFromCart(productModel: IProductModel)

    suspend fun increaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>
    suspend fun decreaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>
}