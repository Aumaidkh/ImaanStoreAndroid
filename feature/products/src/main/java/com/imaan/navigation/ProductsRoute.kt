package com.imaan.navigation

import com.imaan.util.NavigationRoute

object ProductsRoute : NavigationRoute {
    override val route: String
        get() = "products/{query}"

    fun passQueryParam(param: String): String {
        return route.replace("{query}",param)
    }
}