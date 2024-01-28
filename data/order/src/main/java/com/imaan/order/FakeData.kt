package com.imaan.order

import com.imaan.addresses.dummyAddresses
import com.imaan.cart.CartItemModel
import com.imaan.common.model.Amount
import com.imaan.common.model.ID
import com.imaan.common.model.Timestamp
import com.imaan.payment.Card
import com.imaan.payment.CashOnDelivery
import com.imaan.products.getDummyProducts
import java.util.Date


fun getDummyOrders(count: Int): List<OrderModel>{
    val orders = mutableListOf<OrderModel>()
    repeat(count){
        val order = OrderModel(
            orderId = ID("Order$it"),
            cartItems = List(3) { index ->
                CartItemModel(
                    productModel = getDummyProducts(1)[0],
                    quantity = (index + 1) * 2,
                )
            },
            userId = ID("User$it"),
            deliveryCharges = Amount(50.0),
            discount = Amount(25.0),
            status = OrderStatus.StatusPlaced,
            address = dummyAddresses(1)[0],
            paymentMode = if (it % 2 == 0) Card else CashOnDelivery,
            timestamp = Timestamp(System.currentTimeMillis() - (it * 1000 * 60 * 60 * 24)),
            expectedDelivery = Date(System.currentTimeMillis() + (it * 1000 * 60 * 60 * 24 * 3)),
            deliveredOn = if (it % 3 == 0) Date(System.currentTimeMillis() - (it * 1000 * 60 * 60 * 24 * 2)) else null
        )
        orders.add(order)
    }
    return orders
}

