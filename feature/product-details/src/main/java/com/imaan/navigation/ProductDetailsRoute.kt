package com.imaan.navigation

import com.imaan.common.model.ID
import com.imaan.util.NavigationRoute

const val productIdKey = "productId"
object ProductDetailsRoute: NavigationRoute {

    override val route: String
        get() = "product/{$productIdKey}"

    fun passProductId(id: ID): String {
        return route.replace("{$productIdKey}",id.value)
    }
}