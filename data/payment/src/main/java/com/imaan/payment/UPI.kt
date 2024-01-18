package com.imaan.payment

object UPI: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.resources.R.drawable.upi
    override val label: String
        get() = "UPI"
}