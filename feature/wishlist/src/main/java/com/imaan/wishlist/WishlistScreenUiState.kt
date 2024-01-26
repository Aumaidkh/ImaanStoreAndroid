package com.imaan.wishlist

import com.imaan.base_files.BaseScreenState
import com.imaan.products.ProductModel

data class WishlistScreenUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val products: List<ProductModel> = emptyList()
): BaseScreenState
