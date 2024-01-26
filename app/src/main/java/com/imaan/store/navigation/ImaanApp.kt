package com.imaan.store.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.imaan.design_system.components.top_bars.ImaanAppTopBar
import com.imaan.design_system.components.top_bars.Type
import com.imaan.home.navigation.HomeRoute
import com.imaan.home.navigation.homeNavigationProvider
import com.imaan.navigation.AuthRoute
import com.imaan.navigation.CartRoute
import com.imaan.navigation.ManageAddresses
import com.imaan.navigation.ManageAddressesFeature
import com.imaan.navigation.OnboardingRoute
import com.imaan.navigation.Payment
import com.imaan.navigation.ProductsRoute
import com.imaan.navigation.Profile
import com.imaan.navigation.addressesNavigationProvider
import com.imaan.navigation.authNavigationProvider
import com.imaan.navigation.cartNavigationProvider
import com.imaan.navigation.onBoardingNavigationProvider
import com.imaan.navigation.paymentNavigationProvider
import com.imaan.navigation.productsNavigationProvider
import com.imaan.navigation.profileNavigationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "ImaanApp"

@Composable
fun ImaanApp(
    navController: NavHostController,
    startDestination: String = AuthRoute.Feature,
    scope: CoroutineScope
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: ""


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            TopBar(
                currentDestination = currentDestination,
                navController = navController
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            route = "root_graph"
        ) {
            onBoardingNavigationProvider(
                paddingValues = it,
                onNavigateToLogin = {
                    navController.navigate(
                        route = AuthRoute.Feature
                    ){
                        popUpTo(
                            route = OnboardingRoute.route
                        ){
                            inclusive = true
                        }
                    }
                }
            )
            authNavigationProvider(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                onRegisterClick = {
                    navController.navigate(
                        route = AuthRoute.Register.route
                    )
                },
                onOtpSent = {
                    navController.navigate(
                        route = AuthRoute.VerifyOtp.route
                    )
                },
                onSignInClick = {
                    navController.navigate(
                        route = AuthRoute.SignIn.route
                    )
                },
                onAuthenticationSucceeded = {
                    navController.navigate(
                        route = HomeRoute.route
                    ){
                        popUpTo(
                            route = AuthRoute.Feature
                        ){
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )


            homeNavigationProvider(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                onNavigateToCart = {
                    navController.navigate(
                        route = CartRoute.route
                    )
                },
                onNavigateToProductsScreen = {
                    navController.navigate(
                        route = ProductsRoute.passQueryParam(
                            param = it?.label?.value ?: "All Products"
                        )
                    )
                },
                onNavigateToCategories = {
                    navController.navigate(
                        route = ProductsRoute.passQueryParam(
                            param = "All Products"
                        )
                    )
                },
                onNavigateToProfile = {
                    navController.navigate(
                        route = Profile.feature
                    )
                },
                onSignOut = {
                    navController.navigate(
                        route = AuthRoute.Feature
                    ) {
                        popUpTo(HomeRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )

            productsNavigationProvider(
                paddingValues = it,
                snackbarHostState = snackbarHostState,
                onNavigateToProductDetails = {
                    // TODO(Show details for the product)
                }
            )

            cartNavigationProvider(
                paddingValues = it,
                onNavigateToAddresses = {
                    navController.navigate(
                        route = ManageAddressesFeature
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

            addressesNavigationProvider(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                onNavigateToAddAddress = {
                    navController.navigate(
                        route = ManageAddresses.UpsertAddress.passAddressId(it?.id)
                    )
                },
                onNavigateToPayments = {
                    navController.navigate(
                        route = Payment.feature
                    )
                },
                onNavigateToViewAddress = {
                    navController.popBackStack()
                },
            )

            paymentNavigationProvider(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                onNavigateToConfirmationPage = {
                    navController.navigate(
                        route = Payment.Confirmation.route
                    )
                },
                onContinueShopping = {
                    navController.popBackStack(
                        route = HomeRoute.route,
                        inclusive = false
                    )
                },
                onTryAgain = {
                    navController.popBackStack(
                        route = ManageAddressesFeature,
                        inclusive = false
                    )
                },
                onShare = {

                }
            )

            profileNavigationProvider(
                paddingValues = it,
                onNavigateToAddresses = {
                    navController.navigate(
                        route = ManageAddressesFeature
                    )
                },
                onNavigateToEditProfile = {
                    navController.navigate(
                        route = Profile.EditProfile.route
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun TopBar(
    currentDestination: String,
    navController: NavHostController
) {
    when (currentDestination) {
        Profile.ViewProfileRoute.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "View Profile",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        Profile.EditProfile.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "Edit Profile",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        Payment.SelectPaymentMethod.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "Payment",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        ManageAddresses.SavedAddresses.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "View Addresses",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        ManageAddresses.UpsertAddress.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "Add Address",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        ProductsRoute.route -> {
            ImaanAppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                type = Type.WithoutProfilePic,
                title = "Products",
                onNavigationClick = {
                    navController.popBackStack()
                }
            )
        }

        HomeRoute.route -> Unit
    }
}
