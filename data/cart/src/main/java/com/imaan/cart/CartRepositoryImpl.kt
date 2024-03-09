package com.imaan.cart

import com.imaan.cart.CartDatasource.cartItems
import com.imaan.common.model.ID
import com.imaan.products.model.DetailedProductModel
import com.imaan.products.model.IProductModel
import com.imaan.remote.IRemoteDatasource
import com.imaan.remote.dto.Cart
import com.imaan.remote.dto.toInventory
import com.imaan.remote.dto.toProductVariant
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import javax.inject.Inject

internal class CartRepositoryImpl @Inject constructor(
    private val datasource: IRemoteDatasource
) : ICartRepository {
    private val _cartItemsFlow = MutableStateFlow(emptyList<CartItemModel>())
    override val cartItemsFlow: StateFlow<List<CartItemModel>>
        get() = _cartItemsFlow.asStateFlow()

    override suspend fun fetchCartItems(): List<CartItemModel> {
        updateCartItemsWith(cartItems)
        return cartItems
    }

    override suspend fun addProductToCart(productModel: IProductModel): Boolean {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex == -1) {
            //            cartItems.add(
            ////                CartItemModel(
            ////                    productModel
            ////                )
            //            )
            updateCartItemsWith(cartItems)
            return true
        }
        return false
    }

    override suspend fun removeProductFromCart(productModel: IProductModel) {
        val cartItemIndex = cartItems.indexOfFirst { it.productModel == productModel }
        if (cartItemIndex != -1){
            cartItems.remove(cartItems[cartItemIndex])
            updateCartItemsWith(cartItems)
        }
    }

    override suspend fun increaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel> {
//        val updatedItems = cartItems
//        val index = updatedItems.indexOfFirst { it.productModel.id == cartItemModel.productModel.id }
//        if (index != -1) {
//            val item = cartItems[index]
//            updatedItems[index] = item.copy(quantity = item.quantity + 1)
//        }
//        cartItems = updatedItems
//        updateCartItemsWith(cartItems)
        return cartItems
    }

    override suspend fun decreaseCartItemQuantity(cartItemModel: CartItemModel): List<CartItemModel> {
//        val updatedItems = cartItems
//        val index = updatedItems.indexOfFirst { it.productModel.id == cartItemModel.productModel.id }
//        if (index != -1) {
//            val item = cartItems[index]
//            if (item.quantity > 1){
//                updatedItems[index] = item.copy(quantity = item.quantity - 1)
//            } else {
//                updatedItems.removeAt(index)
//            }
//        }
//        cartItems = updatedItems
//        updateCartItemsWith(cartItems)
        return cartItems
    }

    private fun updateCartItemsWith(newItems: List<CartItemModel>) {
        _cartItemsFlow.value = newItems
    }

    /**
     * Does the following
     * 1. Fetch Cart items for a user
     * 2. For each cart Item we need to create a product out of it which is then to be attached in the [CartItemModel] itself
     * 3. The Product Itself is to be created with ProductVariant and Inventory
     * */
    override suspend fun fetchCartItemsFlow(userId: ID): Flow<Result<List<CartItemModel>>> {
        return datasource.fetchCartForUser(userId.value).map { result ->
            when (result) {
                is Result.Success -> {
                    val items = result.data.map {
                        CartItemModel(it)
                    }.distinctBy { it.productModel?.id }
                    Result.Success(items)
                }

                is Result.Error -> Result.Error(result.throwable)
            }
        }
    }

    override suspend fun addItemToCart(productModel: IProductModel): Flow<Result<CartItemModel>> {
        val cartItem = Cart().apply {
            this.quantity = 1
            this.variantId = org.mongodb.kbson.ObjectId(productModel.id.value)
        }
        return datasource.insertCartItem(cart = cartItem).map { result ->
            when (result) {
                is Result.Error -> Result.Error(result.throwable)
                is Result.Success -> Result.Success(data = CartItemModel(cartItem))
            }
        }
    }

    override fun removeItemFromCart(cartItemModel: CartItemModel): Flow<Result<CartItemModel>> {
        if (cartItemModel.id == null) return flowOf(Result.Error(Exception("CartItemModel ID is null")))
        return datasource.removeCartItem(cartItemModel.id.value).map { result ->
            when (result) {
                is Result.Success -> {
                    val item = CartItemModel(result.data)
                    Result.Success(item)
                }
                is Result.Error -> Result.Error(result.throwable)
            }
        }
    }

    override fun updateCartItemQuantity(
        quantity: ICartRepository.Quantity,
        itemModel: CartItemModel
    ): Flow<Result<CartItemModel>> {
        val initialQuantity = itemModel.quantity
        return when(quantity){
            is ICartRepository.Quantity.Increase -> {
                datasource.updateQuantity(itemModel.toCart(initialQuantity + 1)).map {result ->
                    when(result){
                        is Result.Success -> {Result.Success(CartItemModel(result.data))}
                        is Result.Error -> Result.Error(result.throwable)
                    }
                }
            }
            is ICartRepository.Quantity.Decrease -> {
                if (initialQuantity==1){
                    return removeItemFromCart(itemModel)
                }
                datasource.updateQuantity(itemModel.toCart(initialQuantity - 1)).map {result ->
                    when(result){
                        is Result.Success -> {Result.Success(CartItemModel(result.data))}
                        is Result.Error -> Result.Error(result.throwable)
                    }
                }
            }
        }
    }
}