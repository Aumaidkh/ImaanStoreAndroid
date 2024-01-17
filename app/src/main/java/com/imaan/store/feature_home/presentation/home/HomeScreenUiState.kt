package com.imaan.store.feature_home.presentation.home

import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.feature_auth.domain.model.UserModel
import com.imaan.store.feature_home.domain.models.CategoryModel
import com.imaan.store.feature_home.presentation.composables.OfferModel

data class HomeScreenUiState(
    val loading: Boolean = false,
    val user: UserModel? = null,
    val cartItemCount: Int = 0,
    val offers: List<OfferModel> = emptyList(),
    val categories: List<CategoryModel> = emptyList(),
    val selectedCategory: CategoryModel? = categories.firstOrNull(),
    val products: List<ProductModel> = emptyList(),
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<String> = emptyList()
)