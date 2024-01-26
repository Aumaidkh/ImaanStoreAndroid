package com.imaan.order

import com.imaan.common.wrappers.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class OrderRepositoryImpl @Inject constructor(): IOrderRepository{
    private val _orderFlow = MutableStateFlow(OrderModel())
    override val orderFlow: StateFlow<OrderModel>
        get() = _orderFlow.asStateFlow()

    override suspend fun getOrderModel(): OrderModel? {
        return orderFlow.firstOrNull()
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