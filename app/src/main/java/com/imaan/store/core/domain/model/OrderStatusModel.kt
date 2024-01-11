package com.imaan.store.core.domain.model


/**
 * Wrapper around the state in which and
 * order can be. It can be any of the below statuses
 * */
sealed class OrderStatus(val status: String){

    object StatusPending: OrderStatus(Pending)
    object StatusRejected: OrderStatus(Rejected)
    object StatusPlaced: OrderStatus(Placed)
    object StatusShipped: OrderStatus(Shipped)
    object StatusDelivered: OrderStatus(Delivered)
    companion object{
        const val Pending = "Pending"
        const val Rejected = "Rejected"
        const val Placed = "Placed"
        const val Shipped = "Shipped"
        const val Delivered = "Delivered"
    }
}