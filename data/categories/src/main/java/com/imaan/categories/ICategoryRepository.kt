package com.imaan.categories

import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun fetchCategories(): List<CategoryModel>

    fun fetchCategoriesFlow(): Flow<Result<List<CategoryModel>>>
}