package com.imaan.store.feature_home.navigation

sealed class Routes(val route: String){
    object Home: Routes("home")
    object History: Routes("history")
    object Favorites: Routes("favorites")
    object Profile: Routes("profile")
}
