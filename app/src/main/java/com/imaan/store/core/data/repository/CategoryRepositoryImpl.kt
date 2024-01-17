package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.CategoryRepository
import com.imaan.store.feature_home.domain.models.CategoryModel
import com.imaan.store.feature_home.domain.models.getDummyCategories
import kotlinx.coroutines.delay

class CategoryRepositoryImpl: CategoryRepository {

    override suspend fun fetchCategories(): List<CategoryModel> {
    //    delay(3000)
        return getDummyCategories(8)
    }
}