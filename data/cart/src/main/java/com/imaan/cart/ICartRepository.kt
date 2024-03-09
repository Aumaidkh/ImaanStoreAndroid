package com.imaan.cart

import com.imaan.common.model.ID
import com.imaan.products.model.IProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import com.imaan.util.Result

interface ICartRepository {

    val cartItemsFlow: StateFlow<List<CartItemModel>>
    suspend fun fetchCartItems(): List<CartItemModel>
    suspend fun fetchCartItemsFlow(userId: ID): Flow<Result<List<CartItemModel>>>

    suspend fun addItemToCart(productModel: IProductModel): Flow<Result<CartItemModel>>

    fun removeItemFromCart(cartItemModel: CartItemModel): Flow<Result<CartItemModel>>

    fun updateCartItemQuantity(quantity: Quantity,itemModel: CartItemModel): Flow<Result<CartItemModel>>
    suspend fun addProductToCart(productModel: IProductModel): Boolean

    suspend fun removeProductFromCart(productModel: IProductModel)

    suspend fun increaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>
    suspend fun decreaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel>

    sealed interface Quantity {
        object Increase: Quantity

        object Decrease: Quantity
    }
}