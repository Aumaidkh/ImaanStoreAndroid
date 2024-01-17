package com.imaan.products

import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(): IProductRepository {
    override suspend fun fetchAllProducts(offset: Int?): List<ProductModel> {
        return getDummyProducts(20)
    }
}