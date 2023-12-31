package com.imaan.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.imaan.store.feature_auth.navigation.NavigationConstants
import com.imaan.store.feature_auth.navigation.authNavigation

@Composable
fun ImaanApp(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationConstants.AUTH_FEATURE
    ) {
        authNavigation(
            navController = navController
        )
    }
}