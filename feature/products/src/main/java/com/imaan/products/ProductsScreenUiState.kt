package com.imaan.products

import com.imaan.base_files.BaseScreenState

data class ProductsScreenUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val query: String = "Category",
    val products: List<ProductModel> = emptyList(),
    val sortOrder: SortOrder = SortOrder.PRICE_LOW_TO_HIGH,
    val infoMessage: String? = null
):BaseScreenState

enum class SortOrder {
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
}
