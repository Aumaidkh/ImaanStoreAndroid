package com.imaan.details

import com.imaan.base_files.BaseScreenState
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders
import com.imaan.user.UserModel
import com.imaan.user.dummyUser

data class OrderDetailsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val order: OrderModel? = getDummyOrders(1)[0],
    val user: UserModel? = dummyUser
):BaseScreenState
