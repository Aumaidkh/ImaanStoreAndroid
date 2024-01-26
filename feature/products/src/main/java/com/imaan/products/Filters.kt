package com.imaan.products

import com.imaan.common.model.Title
import com.imaan.design_system.R
import com.imaan.design_system.components.views.DropDownMenuItem

enum class Filter(
    override val iconResId: Int,
    override val label: Title
): DropDownMenuItem {
    PRICE_LOW_TO_HIGH(
        iconResId = R.drawable.arrow_up,
        label = Title("Price Low to High")
    ),
    PRICE_HIGH_TO_LOW(
        iconResId = R.drawable.arrow_down,
        label = Title("Price High to Low")
    ),
}