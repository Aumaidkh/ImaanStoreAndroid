package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.CartRepository
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.feature_cart.presentation.addDuumyToCart
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import com.imaan.store.feature_cart.presentation.dummyCart
import kotlinx.coroutines.delay

class CartRepositoryImpl: CartRepository {

    private val cartItems = mutableListOf<CartItemModel>()
    override suspend fun fetchCartItems(): List<CartItemModel> {
        delay(4000)
        return dummyCart
    }

    override suspend fun addToCart(productModel: ProductModel): Boolean {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex == -1){
            return addDuumyToCart(
                productModel
            )
        }
        return false
    }

    override suspend fun removeItemFromCart(productModel: ProductModel) {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex != -1){
            cartItems.remove(cartItems[cartItemIndex])
        }
    }
}