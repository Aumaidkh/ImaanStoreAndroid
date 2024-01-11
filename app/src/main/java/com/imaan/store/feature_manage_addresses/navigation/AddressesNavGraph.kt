package com.imaan.store.feature_manage_addresses.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_manage_addresses.presentation.screen.add.UpsertAddressScreen
import com.imaan.store.feature_manage_addresses.presentation.screen.add.UpsertAddressViewModel
import com.imaan.store.feature_manage_addresses.presentation.screen.view.ViewAddressesScreen
import com.imaan.store.feature_manage_addresses.presentation.screen.view.ViewAddressesViewModel
import com.imaan.store.feature_payment.navigation.PaymentRoute

fun NavGraphBuilder.manageAddressesNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
){
    navigation(
        startDestination = AddressesRoute.PickAddress.route,
        route = ManageAddresses
    ){
        composable(
            route = AddressesRoute.PickAddress.route
        ){
            val viewModel = hiltViewModel<ViewAddressesViewModel>()
            val state = viewModel.state.collectAsState().value
            val event = viewModel.event.collectAsState(initial = null).value

            LaunchedEffect(key1 = event){
                when(event){
                    is UiEvent.Success<*> -> {
                        navController.navigate(
                            route = PaymentRoute().route
                        )
                    }

                    is UiEvent.Error -> {
                        snackbarHostState.showSnackbar(
                            message = event.errorMessage
                        )
                    }

                    else -> {

                    }
                }
            }

            ViewAddressesScreen(
                state = state,
                onAddressSelected = viewModel::onAddressSelected,
                onDeliverToAddress = viewModel::onDeliverToAddress,
                onEditAddressClick = {
                    navController.navigate(
                        route = AddressesRoute.AddNewAddress.passAddress(it)
                    )
                },
                onAddNewAddressClick = {
                    navController.navigate(
                        route = AddressesRoute.AddNewAddress.route
                    )
                }
            )
        }

        composable(
            route = AddressesRoute.AddNewAddress.route
        ){
            val viewModel = hiltViewModel<UpsertAddressViewModel>()
            val state = viewModel.state.collectAsState().value

            UpsertAddressScreen(
                state = state,
                onBackClick = {
                    navController.popBackStack()
                },
                onFullNameChange = viewModel::onFullNameChange,
                onLandmarkChange = viewModel::onLandmarkChange,
                onCityChange = viewModel::onCityChange,
                onDistrictChange = viewModel::onDistrictChange,
                onStateChange = viewModel::onStateChange,
                onPhoneChange = viewModel::onPhoneChange,
                onCountryChange = viewModel::onCountryChange,
                onPinCodeChange = viewModel::onPostalCodeChange,
                onFullAddressChange = viewModel::onFullAddressChange,
                onSaveClick = {}
            )
        }
    }
}