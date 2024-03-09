package com.imaan.remote

import com.imaan.remote.dto.Address
import com.imaan.remote.dto.Cart
import com.imaan.remote.dto.Category
import com.imaan.remote.dto.Inventory
import com.imaan.remote.dto.Product
import com.imaan.remote.dto.ProductVariant
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow

interface IRemoteDatasource {
    suspend fun fetchProducts(): Flow<Result<List<Product>>>
    suspend fun fetchProductWithId(id: String): Flow<Result<List<Product>>>
    suspend fun fetchInventories(): Flow<Result<List<Inventory>>>
    suspend fun fetchInventoriesForProduct(id: String): Flow<Result<List<Inventory>>>
    suspend fun fetchVariantsForProduct(id: String): Flow<Result<List<ProductVariant>>>
    suspend fun insertProduct()

    fun fetchProductsByCategory(category: String,offset: String): Flow<Result<List<Product>>>

    /*###################################################################################
   * #                             Product Variants                                    #
   * ##################################################################################*/
    /**
     * Fetch a list of [Inventory]
     * @param variantId's.
     * @return can be a [Result.Success] or [Result.Error]*/
   // suspend fun fetchProductVariantsWithIds(vararg variantId: String): Flow<Result<List<Inventory>>>

    /*###################################################################################
    * #                             Inventories                                         #
    * ##################################################################################*/
    /**
     * Fetch a list of [Inventory]
     * @param variantId's.
     * @return can be a [Result.Success] or [Result.Error]*/
   // suspend fun fetchInventoriesWithVariantIds(vararg variantId: String): Flow<Result<List<Inventory>>>


    /*###################################################################################
    * #                             Addresses                                           #
    * ##################################################################################*/
    suspend fun fetchAddressById(addressId: String): Flow<Result<Address>>
    suspend fun fetchAllAddressesForUser(userId: String): Flow<Result<List<Address>>>

    suspend fun insertAddress(userId: String,address: Address): Flow<Result<Address>>
    suspend fun updateAddress(userId: String,address: Address): Flow<Result<Address>>

    /*###################################################################################
   * #                                  Cart                                           #
   * ##################################################################################*/

    /**
     * Fetch a list of [Cart] which the user
     * @param userId has in his Cart.
     * @return can be a [Result.Success] or [Result.Error]*/
    suspend fun fetchCartForUser(userId: String): Flow<Result<List<Cart>>>

    /**
     * Insert
     * @param cart inside [Cart]
     * @return the same item wrapped inside a [Flow] of
     * either [Result.Success] or [Result.Error]*/
    suspend fun insertCartItem(cart: Cart): Flow<Result<Cart>>

    /**
     * Update the quantity
     * @param item
     * @return the same item wrapped inside a [Flow] of
     * either [Result.Success] or [Result.Error]*/
    fun updateQuantity(item: Cart): Flow<Result<Cart>>

    /**
     * Remove the
     * @param itemId from the cart
     * @return the same item wrapped inside a [Flow] of
     * either [Result.Success] or [Result.Error]*/
    fun removeCartItem(itemId: String): Flow<Result<Cart>>

    /*###################################################################################
*   #                                  Category                                        #
*   ##################################################################################*/

    /**
     * Fetching categories
     * */
    fun fetchCategories(): Flow<Result<List<Category>>>

}