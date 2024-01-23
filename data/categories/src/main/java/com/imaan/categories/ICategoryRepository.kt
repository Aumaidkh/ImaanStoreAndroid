package com.imaan.categories

interface ICategoryRepository {
    suspend fun fetchCategories(): List<CategoryModel>
}