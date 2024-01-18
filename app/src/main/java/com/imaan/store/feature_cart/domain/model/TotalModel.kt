package com.imaan.store.feature_cart.domain.model

import com.imaan.store.core.domain.model.Amount

data class TotalModel(
    val deliveryCharges : Amount,
    val subtotal: Amount,
    val discount: Amount,
    val grandTotal: Amount
)