package com.imaan.order

import com.imaan.addresses.Address
import com.imaan.addresses.Address.Companion.BLANK_ADDRESS
import com.imaan.cart.CartItemModel
import com.imaan.common.model.Amount
import com.imaan.common.model.ID
import com.imaan.common.model.Timestamp
import com.imaan.payment.Card
import com.imaan.payment.PaymentMode
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
    val orderId: ID? = null,
    val cartItems: List<CartItemModel> = emptyList(),
    val userId: ID = ID(""),
    val deliveryCharges: Amount = Amount.ZERO,
    val discount: Amount = Amount.ZERO,
    val status: OrderStatus = OrderStatus.StatusPending,
    val address: Address = BLANK_ADDRESS,
    val paymentMode: PaymentMode = Card,
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis()),
    val expectedDelivery: Date = Date(),
    val deliveredOn: Date? = null
) {
    val stringId get() = "#123131213131"

    val orderSubtotal get() = run {
        var totalSum = Amount.ZERO
        cartItems.forEach {
            totalSum = totalSum plus (it.totalAmount ?: Amount.ZERO)
        }
        totalSum
    }

    val grandTotal get() = orderSubtotal plus deliveryCharges minus discount
}

