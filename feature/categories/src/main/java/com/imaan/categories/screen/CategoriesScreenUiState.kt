package com.imaan.categories.screen

import com.imaan.categories.CategoryModel

data class CategoriesScreenUiState(
    val loading: Boolean = false,
    val categories: List<CategoryModel> = emptyList(),
)
