package com.imaan.products

import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(): IProductRepository {
    override suspend fun fetchAllProducts(offset: Int?): List<ProductModel> {
        return getDummyProducts(20)
    }

    override suspend fun fetchProductWithId(id: ID): Result<ProductModel> {
        return Result.Success(
            data = dummyProduct
        )
    }
}