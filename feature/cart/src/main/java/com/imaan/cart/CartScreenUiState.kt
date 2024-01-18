package com.imaan.cart

import com.imaan.common.model.Amount
import com.imaan.common.model.plus
import com.imaan.common.model.sumOfAmounts
import com.imaan.total.TotalModel

data class CartScreenState(
    val items: List<CartItemModel> = emptyList(),
    val totalModel: TotalModel = TotalModel(
        deliveryCharges = Amount(40.0),
        subtotal = items.sumOfAmounts { it.totalPrice },
        discount = Amount(40.00),
        grandTotal = ((40.0 - 20.0) plus items.sumOfAmounts { it.totalPrice })
    )
)