package com.imaan.products

import com.imaan.common.model.ID
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    suspend fun insertProduct()
    suspend fun fetchAllProducts(offset: Int? = null): List<ProductModel>
    suspend fun fetchAllProductsAsFlow(offset: Int? = null): Flow<Result<List<ProductModel>>>

    suspend fun fetchProductWithId(id: ID): Result<ProductModel>
}
