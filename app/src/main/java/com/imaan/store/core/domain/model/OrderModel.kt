package com.imaan.store.core.domain.model

import com.imaan.store.core.domain.model.Address.Companion.BLANK_ADDRESS
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import com.imaan.store.feature_payment.presentation.Card
import com.imaan.store.feature_payment.presentation.PaymentMode
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * Fields
 * 1. Order Id
 * 2. Cart
 * 3. User Id
 * 4. Total Amount
 * 5. Status
 * 6. Address
 * 7. Payment Mode
 * 8. TimeStamp
 * 9. Actual Delivery Date
 * 10. Expected Delivery Date
 * 11. Total Delivery Charges
 * */
data class OrderModel(
    val id: OrderId? = null,
    val cartItems: List<CartItemModel> = emptyList(),
    val userId: UserId = UserId.getUserId(),
    val deliveryCharges: Amount = Amount.ZERO,
    val discount: Amount = Amount.ZERO,
    val subtotalAmount: Amount = Amount.ZERO,
    val totalAmount: Amount = Amount.ZERO,
    val status: OrderStatus = OrderStatus.StatusPending,
    val address: Address = BLANK_ADDRESS,
    val paymentMode: PaymentMode = Card,
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis()),
    val expectedDelivery: Date = Date(),
    val deliveredOn: Date? = null
)
@JvmInline
value class Timestamp(val value: Long)

@Serializable
@JvmInline
value class UserId(val value: String){
    companion object {
        fun getUserId():UserId {
            return UserId("")
        }
    }
}
@JvmInline
value class OrderId(val value: String)


