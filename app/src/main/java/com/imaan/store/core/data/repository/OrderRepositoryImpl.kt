package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.OrderRepository
import com.imaan.store.core.domain.model.OrderModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(): OrderRepository {

    private val _orderFlow = MutableStateFlow(OrderModel())
    override val orderFlow: StateFlow<OrderModel>
        get() = _orderFlow.asStateFlow()

    override suspend fun getOrderModel(): OrderModel? {
        return orderFlow.firstOrNull()
    }

    override suspend fun updateOrder(orderModel: OrderModel) {
        _orderFlow.value = orderModel
    }
}