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
import com.imaan.home.navigation.homeNavigationProvider
import com.imaan.navigation.CartRoute
import com.imaan.navigation.cartNavigationProvider
import com.imaan.store.feature_auth.navigation.NavigationConstants
import com.imaan.store.feature_auth.navigation.authNavigation
import com.imaan.store.feature_cart.navigation.cartNavigation
import com.imaan.store.feature_home.navigation.homeNavigation
import com.imaan.store.feature_manage_addresses.navigation.AddressesRoute
import com.imaan.store.feature_manage_addresses.navigation.ManageAddresses
import com.imaan.store.feature_manage_addresses.navigation.manageAddressesNavigation
import com.imaan.store.feature_manage_addresses.presentation.screen.add.ADDRESS_KEY
import com.imaan.store.feature_payment.navigation.paymentNavigation
import com.imaan.store.feature_profile.navigation.profileNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "ImaanApp"
@Composable
fun ImaanApp(
    navController: NavHostController,
    startDestination: String = NavigationConstants.AUTH_FEATURE,
    scope: CoroutineScope
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


            homeNavigationProvider(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                onNavigateToCart = {
                    navController.navigate(
                        route = CartRoute.route
                    )
                },
                onNavigateToCategories = {}
            )

            cartNavigationProvider(
                paddingValues = it,
                onNavigateToAddresses = {
                    navController.navigate(
                        route = ManageAddresses
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onError = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = it.message.toString()
                        )
                    }
                }
            )

            manageAddressesNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )

            paymentNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )

            profileNavGraph(
                navController = navController
            )
        }
    }
}