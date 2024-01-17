package com.imaan.store.core.domain

import com.imaan.store.feature_home.domain.models.CategoryModel

interface CategoryRepository {

    suspend fun fetchCategories(): List<CategoryModel>
}