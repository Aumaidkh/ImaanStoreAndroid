package com.imaan.store.feature_home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.store.feature_cart.navigation.CartScreen
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_FEATURE
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_ROUTE
import com.imaan.store.feature_home.presentation.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) fun NavGraphBuilder.homeNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {

    navigation(
        route = HOME_FEATURE,
        startDestination = HOME_ROUTE
    ) {
        composable(
            route = HOME_ROUTE
        ) {
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            NavDrawer(
                drawerState = drawerState,
                onCloseIconClick = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                content = {
                    HomeScreen(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        paddingValues = paddingValues,
                        onCartClick = {
                            navController.navigate(
                                route = CartScreen
                            )
                        }
                    )
                }
            )
        }
    }
}