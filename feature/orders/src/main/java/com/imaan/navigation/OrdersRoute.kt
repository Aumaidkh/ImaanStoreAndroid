package com.imaan.navigation

import com.imaan.common.model.ID
import com.imaan.util.NavigationRoute

sealed interface Orders: NavigationRoute {
    object OrderHistory: Orders {
        override val route: String
            get() = "order-history"
    }

    object OrderDetails: Orders {
        override val route: String
            get() = "order-details/{orderId}"

        fun passOrderId(orderId: ID):String{
            return route.replace("{orderId}",orderId.value)
        }
    }

    companion object{
        const val Feature = "orders"
    }
}