package com.imaan.store.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.imaan.store.feature_auth.navigation.NavigationConstants
import com.imaan.store.feature_auth.navigation.authNavigation
import com.imaan.store.feature_cart.navigation.cartNavigation
import com.imaan.store.feature_home.navigation.homeNavigation

private const val TAG = "ImaanApp"
@Composable
fun ImaanApp(
    navController: NavHostController,
    startDestination: String =  NavigationConstants.AUTH_FEATURE
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            authNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )

            homeNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )

            cartNavigation(
                navController = navController,
                paddingValues = it,
                snackbarHostState = snackbarHostState
            )
        }
    }
}