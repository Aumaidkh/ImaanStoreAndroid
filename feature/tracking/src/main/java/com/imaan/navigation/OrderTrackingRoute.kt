package com.imaan.navigation

import com.imaan.common.model.ID
import com.imaan.util.NavigationRoute

const val OrderIdKey = "OrderIdKey"
object OrderTrackingRoute : NavigationRoute{

    override val route: String
        get() = "track-order/{$OrderIdKey}"

    fun passOrderId(id: ID): String {
        return route.replace("{$OrderIdKey}",id.value)
    }
}