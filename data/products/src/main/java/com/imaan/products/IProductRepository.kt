package com.imaan.products

import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result

interface IProductRepository {
    suspend fun fetchAllProducts(offset: Int? = null): List<ProductModel>

    suspend fun fetchProductWithId(id: ID): Result<ProductModel>
}
