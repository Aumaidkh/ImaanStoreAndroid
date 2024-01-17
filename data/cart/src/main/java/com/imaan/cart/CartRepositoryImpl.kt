package com.imaan.cart

import com.imaan.products.ProductModel
import javax.inject.Inject

internal class CartRepositoryImpl @Inject constructor(): ICartRepository {

    private val cartItems = mutableListOf<CartItemModel>()
    override suspend fun fetchCartItems(): List<CartItemModel> {
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
            return true
        }
        return false
    }

    override suspend fun removeProductFromCart(productModel: ProductModel) {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex != -1){
            cartItems.remove(cartItems[cartItemIndex])
        }
    }
}