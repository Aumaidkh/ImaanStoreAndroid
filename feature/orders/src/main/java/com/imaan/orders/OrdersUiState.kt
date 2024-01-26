package com.imaan.orders

import com.imaan.base_files.BaseScreenState
import com.imaan.order.Orders


data class OrdersUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val infoMessage: String? = null,
    val orders: Orders = emptyList()
):BaseScreenState
