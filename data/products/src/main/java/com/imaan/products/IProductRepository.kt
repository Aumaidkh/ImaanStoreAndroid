package com.imaan.products

interface IProductRepository {
    suspend fun fetchAllProducts(offset: Int? = null): List<ProductModel>
}