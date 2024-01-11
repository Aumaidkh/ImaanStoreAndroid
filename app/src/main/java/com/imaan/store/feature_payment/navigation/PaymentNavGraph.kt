package com.imaan.store.feature_payment.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.imaan.store.feature_payment.presentation.PaymentScreen
import com.imaan.store.feature_payment.presentation.PaymentViewModel

private const val TAG = "PaymentNavGraph"
fun NavGraphBuilder.paymentNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
){
    composable(
        route = PaymentRoute().route
    ){
        val viewModel = hiltViewModel<PaymentViewModel>()
        val state = viewModel.state.collectAsState().value

        PaymentScreen(
            state = state,
            onCardNumberChange = viewModel::onCardNumberChange,
            onCardHolderNameChange = viewModel::onNameChange,
            onCardExpiryChange = viewModel::onExpiryChange,
            onCvvChange = viewModel::onCvvChange,
            onPaymentTypeClick = viewModel::onPaymentModeClick,
            paddingValues = paddingValues
        )
    }
}