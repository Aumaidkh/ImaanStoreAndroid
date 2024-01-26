package com.imaan.wishlist

import com.imaan.base_files.BaseScreenState
import com.imaan.products.ProductModel
import com.imaan.products.getDummyProducts

data class WishlistScreenUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val products: List<ProductModel> = getDummyProducts(10)
): BaseScreenState
