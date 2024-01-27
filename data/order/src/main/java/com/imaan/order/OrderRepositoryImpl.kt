package com.imaan.order

import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class OrderRepositoryImpl @Inject constructor(): IOrderRepository{
    private val _orderFlow = MutableStateFlow(OrderModel())
    override val orderFlow: StateFlow<OrderModel>
        get() = _orderFlow.asStateFlow()

    override suspend fun unplacedOrder(): OrderModel? {
        return orderFlow.firstOrNull()
    }

    override suspend fun getOrderById(id: ID): Result<OrderModel?> {
        return Result.Success(
            data = getDummyOrders(1)[0]
        )
    }

    override suspend fun updateOrder(orderModel: OrderModel) {
        _orderFlow.value = orderModel
    }

    override suspend fun fetchAllOrdersOfUser(userId: String): Result<Orders> {
        return Result.Success(
            data = getDummyOrders(7)
        )
    }
}