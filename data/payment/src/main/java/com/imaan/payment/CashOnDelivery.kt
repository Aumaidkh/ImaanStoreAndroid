package com.imaan.payment

object CashOnDelivery: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.resources.R.drawable.reshot_icon_send_money_h9mlb4d7ez
    override val label: String
        get() = "COD"
}