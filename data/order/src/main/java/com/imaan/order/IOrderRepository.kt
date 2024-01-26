package com.imaan.order

import com.imaan.common.wrappers.Result
import kotlinx.coroutines.flow.StateFlow

typealias Orders = List<OrderModel>
interface IOrderRepository {

    val orderFlow: StateFlow<OrderModel>
    suspend fun getOrderModel(): OrderModel?

    suspend fun updateOrder(orderModel: OrderModel)

    suspend fun fetchAllOrdersOfUser(userId: String): Result<Orders>
}