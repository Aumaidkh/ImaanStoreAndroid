package com.imaan.product_details

import com.imaan.base_files.BaseScreenState
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.ProductModel
import com.imaan.products.SizeVariant
import com.imaan.products.model.DetailedProductModel
import com.imaan.products.model.IProductVariant
import com.imaan.products.model.ProductColorVariant
import com.imaan.products.model.ProductCustomVariant

data class ProductDetailsScreenUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val product: DetailedProductModel? = null,
    val recommendedProducts: List<ProductModel> = emptyList(),
    val buyProductNow: Boolean = false,
    val selectedProductVariant: IProductVariant? = product?.variants?.firstOrNull(),
    val selectedColorVariant: IProductVariant? = null,
    val selectedSizeVariant: IProductVariant? = null,
    val selectedCustomVariant: IProductVariant? = null,
) : BaseScreenState {
    val availableColors get() = product?.variants?.filterIsInstance<ProductColorVariant>() ?: emptyList()
    val showColorPickerComponent get() = availableColors.isNotEmpty()
    val availableCustomVariants get() = product?.variants?.filterIsInstance<ProductCustomVariant>() ?: emptyList()

}


