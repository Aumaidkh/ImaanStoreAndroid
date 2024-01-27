package com.imaan.navigation

import com.imaan.common.model.ID
import com.imaan.util.NavigationRoute

const val OrderIdKey = "orderIdKey"
sealed interface Orders: NavigationRoute {
    object OrderHistory: Orders {
        override val route: String
            get() = "order-history"
    }

    object OrderDetails: Orders {
        override val route: String
            get() = "order-details/{$OrderIdKey}"

        fun passOrderId(orderId: ID):String{
            return route.replace("{$OrderIdKey}",orderId.value)
        }
    }

    companion object{
        const val Feature = "orders"
    }
}