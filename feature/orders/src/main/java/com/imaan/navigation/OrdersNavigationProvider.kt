package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.details.OrderDetailsScreen
import com.imaan.details.OrderDetailsScreenViewModel
import com.imaan.order.OrderModel
import com.imaan.orders.OrderHistoryScreen
import com.imaan.orders.OrdersScreenViewModel

fun NavGraphBuilder.ordersNavigationProvider(
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onOrderClick: (OrderModel) -> Unit
){
    navigation(
        startDestination = Orders.OrderHistory.route,
        route = Orders.Feature
    ){
        composable(
            route = Orders.OrderHistory.route
        ){
            val viewModel = hiltViewModel<OrdersScreenViewModel>()
            val uiState = viewModel.state.collectAsState()

            OrderHistoryScreen(
                uiState = uiState.value,
                paddingValues = paddingValues,
                onOrderClick = onOrderClick
            )
        }

        composable(
            route = Orders.OrderDetails.route
        ){
            val viewModel = hiltViewModel<OrderDetailsScreenViewModel>()
            val uiState = viewModel.state.collectAsState()
            OrderDetailsScreen(
                uiState = uiState.value,
                paddingValues = paddingValues
            )
        }
    }
}