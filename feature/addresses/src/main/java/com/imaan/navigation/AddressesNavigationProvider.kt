package com.imaan.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.addresses.Address
import com.imaan.addresses.AddressesEvent
import com.imaan.addresses.add_addresses.UpsertAddressScreen
import com.imaan.addresses.add_addresses.UpsertAddressViewModel
import com.imaan.addresses.view_addresses.ViewAddressesScreen
import com.imaan.addresses.view_addresses.ViewAddressesViewModel

fun NavGraphBuilder.addressesNavigationProvider(
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    onNavigateToAddAddress: (Address?) -> Unit,
    onNavigateToViewAddress: () -> Unit,
    onNavigateToPayments: () -> Unit,
) {
    navigation(
        startDestination = ManageAddresses.SavedAddresses.route,
        route = ManageAddressesFeature
    ) {
        composable(
            route = ManageAddresses.SavedAddresses.route,
            enterTransition = {
                slideRightEnterTransition()
            },
            exitTransition = {
                slideLeftExitTransition()
            },
            popEnterTransition = {
                slideRightEnterTransition()
            },
            popExitTransition = {
                slideLeftExitTransition()
            }
        ) {
            val viewModel = hiltViewModel<ViewAddressesViewModel>()
            val state = viewModel.state.collectAsState().value


            LaunchedEffect(key1 = state.deliveryAddress) {
                state.deliveryAddress?.let{
                    onNavigateToPayments()
                    viewModel.onDeliveryAddressHandled()
                }
                state.errorMessage?.let {
                    snackbarHostState.showSnackbar(
                        message = it
                    ).also { result ->
                        when(result){
                            SnackbarResult.Dismissed -> {
                                viewModel.onErrorShown()
                            }
                            else -> {}
                        }
                    }
                }
            }

            ViewAddressesScreen(
                state = state,
                onAddressSelected = viewModel::onAddressSelected,
                onDeliverToAddress = viewModel::onDeliverToAddress,
                onEditAddressClick = {
                    onNavigateToAddAddress(it)
                },
                onAddNewAddressClick = {
                    onNavigateToAddAddress(null)
                },
            )
        }

        composable(
            route = ManageAddresses.UpsertAddress.route,
            enterTransition = {
                slideRightEnterTransition()
            },
            exitTransition = {
                slideLeftExitTransition()
            },
            popEnterTransition = {
                slideRightEnterTransition()
            },
            popExitTransition = {
                slideLeftExitTransition()
            }
        ) {
            val viewModel = hiltViewModel<UpsertAddressViewModel>()
            val state = viewModel.state.collectAsState().value

            LaunchedEffect(key1 = state.addressProcessed){
                if (state.addressProcessed){
                    onNavigateToViewAddress()
                    viewModel.addressProcessed()
                }
            }

            UpsertAddressScreen(
                state = state,
                onFullNameChange = viewModel::onFullNameChange,
                onLandmarkChange = viewModel::onLandmarkChange,
                onCityChange = viewModel::onCityChange,
                onDistrictChange = viewModel::onDistrictChange,
                onStateChange = viewModel::onStateChange,
                onPhoneChange = viewModel::onPhoneChange,
                onCountryChange = viewModel::onCountryChange,
                onPinCodeChange = viewModel::onPostalCodeChange,
                onFullAddressChange = viewModel::onFullAddressChange,
                onSaveClick = viewModel::saveAddress,
                paddingValues = paddingValues
            )
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideRightEnterTransition(): EnterTransition {
    return fadeIn(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideLeftExitTransition(): ExitTransition {
    return fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}