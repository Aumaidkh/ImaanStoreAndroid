package com.imaan.products.model

import com.imaan.common.model.Amount
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Stocks

interface IProductVariant {
    val id: ID
    val label: String
    val stocks: Stocks
    val price: Amount
    val discount: Discount
    val images: List<Image>
    val size: Size?
}

class ProductColorVariant(
    override val id: ID,
    override val label: String,
    override val stocks: Stocks,
    override val price: Amount,
    override val discount: Discount,
    override val images: List<Image>,
    override val size: Size?,
    val hexColor: String
):IProductVariant

class ProductCustomVariant(
    override val id: ID,
    override val label: String,
    override val stocks: Stocks,
    override val price: Amount,
    override val discount: Discount,
    override val images: List<Image>,
    override val size: Size?
):IProductVariant {


}

enum class Size(val label: String) {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    EXTRA_LARGE("Extra Small"),
    XXL("Extra Extra Large")
}