package com.imaan.store.core.domain

import com.imaan.store.core.domain.model.ProductModel

interface ProductsRepository {
    suspend fun fetchAllProducts(offset: Int? = null): List<ProductModel>
}