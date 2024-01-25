package com.imaan.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.common.wrappers.Result
import com.imaan.payments.confirmation.PaymentConfirmationScreen
import com.imaan.payments.PaymentScreen
import com.imaan.payments.PaymentViewModel

private const val TAG = "PaymentNavGraph"
fun NavGraphBuilder.paymentNavigationProvider(
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    onNavigateToConfirmationPage: () -> Unit,
    onContinueShopping: () -> Unit,
    onShare: () -> Unit,
    onTryAgain: () -> Unit,
){
    navigation(
        route = Payment.feature,
        startDestination = Payment.SelectPaymentMethod.route
    ){
        composable(
            route =  Payment.SelectPaymentMethod.route
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
                paddingValues = paddingValues,
                onPay = {
                    onNavigateToConfirmationPage()
                }
            )
        }

        composable(
            route =  Payment.Confirmation.route
        ){
            PaymentConfirmationScreen(
                paymentResult = Result.Success("af"),
                paddingValues = paddingValues,
                onShare = onShare,
                onTryAgain = onTryAgain,
                onContinueShopping = onContinueShopping
            )
        }
    }

}