package com.imaan.total

import com.imaan.common.model.Amount


data class TotalModel(
    val deliveryCharges : Amount,
    val subtotal: Amount,
    val discount: Amount,
    val grandTotal: Amount
)