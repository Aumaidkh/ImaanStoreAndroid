package com.imaan.categories

import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(): ICategoryRepository {
    override suspend fun fetchCategories(): List<CategoryModel> {
        return getDummyCategories(9)
    }

}

