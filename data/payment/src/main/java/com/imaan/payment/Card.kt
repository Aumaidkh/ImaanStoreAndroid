package com.imaan.payment


object Card: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.resources.R.drawable.card
    override val label: String
        get() = "Card"
}