package com.imaan.store.feature_payment.presentation

import com.imaan.store.R
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.domain.model.Amount
import com.imaan.store.core.domain.model.dummyAddress
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import com.imaan.store.feature_payment.domain.model.DebitCard
import com.imaan.store.feature_payment.domain.model.dummyCard

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
        get() = R.drawable.card
    override val label: String
        get() = "Card"
}
object CashOnDelivery: PaymentMode{
    override val iconResId: Int
        get() = R.drawable.reshot_icon_send_money_h9mlb4d7ez
    override val label: String
        get() = "COD"
}
object UPI: PaymentMode{
    override val iconResId: Int
        get() = R.drawable.upi
    override val label: String
        get() = "UPI"
}

val paymentModes = listOf(
    Card,
    CashOnDelivery,
    UPI
)
