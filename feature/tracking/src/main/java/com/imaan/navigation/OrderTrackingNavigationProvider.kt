package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.tracking.OrderTrackingScreenViewModel
import com.imaan.tracking.TrackOrderScreen

fun NavGraphBuilder.orderTrackingNavigationProvider(
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onContactSeller: () -> Unit,
){
    composable(
        route = OrderTrackingRoute.route
    ){
        val viewModel = hiltViewModel<OrderTrackingScreenViewModel>()
        val state = viewModel.state.collectAsState()

        LaunchedEffect(key1 = state.value.errorMessage){
            state.value.errorMessage?.let {
                snackbarHostState.showSnackbar(message = it).also { snackbarResult ->
                    when(snackbarResult){
                        SnackbarResult.Dismissed -> {
                            viewModel.onErrorHandled()
                        }
                        else -> Unit
                    }
                }
            }
        }

        TrackOrderScreen(
            paddingValues = paddingValues,
            uiState = state.value,
            onContactSeller = onContactSeller
        )
    }
}