package com.imaan.tracking

import com.imaan.base_files.BaseScreenState
import com.imaan.common.model.ID
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders
import com.imaan.user.UserModel
import com.imaan.user.dummyUser

data class OrderTrackingUiState(
    val orderID: ID? = null,
    val order: OrderModel? = null,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val user: UserModel? = dummyUser
): BaseScreenState
