package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.ProductsRepository
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.core.domain.model.getDummyProducts
import kotlinx.coroutines.delay

class ProductRepositoryImpl: ProductsRepository {

    override suspend fun fetchAllProducts(offset: Int?): List<ProductModel> {
        //delay(4000)
        return getDummyProducts(20)
    }
}