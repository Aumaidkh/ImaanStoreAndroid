package com.imaan.store.core.domain

import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.feature_cart.presentation.composable.CartItemModel

interface CartRepository {

    suspend fun fetchCartItems(): List<CartItemModel>

    suspend fun addToCart(productModel: ProductModel): Boolean

    suspend fun removeItemFromCart(productModel: ProductModel)
}