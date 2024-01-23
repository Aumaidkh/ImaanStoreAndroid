package com.imaan.payments

import com.imaan.addresses.Address
import com.imaan.addresses.dummyAddress
import com.imaan.cart.CartItemModel
import com.imaan.common.model.Amount
import com.imaan.payments.util.DebitCard
import com.imaan.payments.util.dummyCard

data class PaymentScreenUiState(
    val loading: Boolean = false,
    val paymentMode: PaymentMode = Card,
    val card: DebitCard = dummyCard,
    val deliveryCharges: Amount = Amount.ZERO,
    val subtotal: Amount = Amount.ZERO,
    val totalAmount: Amount = Amount.ZERO,
    val address: Address = dummyAddress(),
    val cartItems: List<CartItemModel> = emptyList()
)

interface PaymentMode{

    val iconResId: Int
    val label: String
}
object Card: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.design_system.R.drawable.card
    override val label: String
        get() = "Card"
}
object CashOnDelivery: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.design_system.R.drawable.reshot_icon_send_money_h9mlb4d7ez
    override val label: String
        get() = "COD"
}
object UPI: PaymentMode{
    override val iconResId: Int
        get() = com.imaan.design_system.R.drawable.upi
    override val label: String
        get() = "UPI"
}

val paymentModes = listOf(
    Card,
    CashOnDelivery,
    UPI
)
