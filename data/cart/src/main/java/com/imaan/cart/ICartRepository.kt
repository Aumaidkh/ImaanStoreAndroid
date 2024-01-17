package com.imaan.cart

import com.imaan.products.ProductModel

interface ICartRepository {
    suspend fun fetchCartItems(): List<CartItemModel>

    suspend fun addProductToCart(productModel: ProductModel): Boolean

    suspend fun removeProductFromCart(productModel: ProductModel)
}