package com.imaan.home.ui

import com.imaan.categories.CategoryModel
import com.imaan.offers.OfferModel
import com.imaan.products.ProductModel
import com.imaan.products.model.IProductModel
import com.imaan.user.UserModel

data class HomeScreenUiState(
    val loading: Boolean = false,
    val user: UserModel? = null,
    val cartItemCount: Int = 0,
    val offers: List<OfferModel> = emptyList(),
    val categories: List<CategoryModel> = emptyList(),
    val selectedCategory: CategoryModel? = categories.firstOrNull(),
    val products: List<IProductModel> = emptyList(),
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<String> = emptyList()
)