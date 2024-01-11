package com.imaan.store.core.domain

import com.imaan.store.core.domain.model.OrderModel
import kotlinx.coroutines.flow.StateFlow

interface OrderRepository {

    val orderFlow: StateFlow<OrderModel>
    suspend fun getOrderModel(): OrderModel?

    suspend fun updateOrder(orderModel: OrderModel)
}