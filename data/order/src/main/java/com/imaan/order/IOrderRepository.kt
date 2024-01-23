package com.imaan.order

import kotlinx.coroutines.flow.StateFlow

interface IOrderRepository {

    val orderFlow: StateFlow<OrderModel>
    suspend fun getOrderModel(): OrderModel?

    suspend fun updateOrder(orderModel: OrderModel)
}