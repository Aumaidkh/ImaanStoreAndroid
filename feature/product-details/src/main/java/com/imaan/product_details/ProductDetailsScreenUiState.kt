package com.imaan.product_details

import com.imaan.base_files.BaseScreenState
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.ProductModel
import com.imaan.products.SizeVariant

data class ProductDetailsScreenUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val product: ProductModel? = null,
    val recommendedProducts: List<ProductModel> = emptyList(),
    val selectedSize: SizeVariant? = product?.sizes?.firstOrNull(),
    val selectedColor: ColorVariant? = product?.colors?.firstOrNull(),
    val selectedVariant: CustomVariant? = product?.customVariants?.firstOrNull(),

    val buyProductNow: Boolean = false
): BaseScreenState

