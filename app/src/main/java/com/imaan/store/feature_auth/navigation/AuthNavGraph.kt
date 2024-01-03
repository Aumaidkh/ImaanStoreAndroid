package com.imaan.store.feature_auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.store.feature_auth.navigation.NavigationConstants.AUTH_FEATURE
import com.imaan.store.feature_auth.presentation.login.LoginScreen
import com.imaan.store.feature_auth.presentation.login.LoginViewModel
import com.imaan.store.feature_auth.presentation.register.RegisterScreen
import com.imaan.store.feature_auth.presentation.register.ScreenViewModel
import com.imaan.store.feature_auth.presentation.verify_otp.VerifyOtpScreen
import com.imaan.store.feature_auth.presentation.verify_otp.VerifyOtpScreenViewModel

private const val TAG = "AuthNavGraph"
fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues = PaddingValues(),
) {
    navigation(
        route = AUTH_FEATURE,
        startDestination = AuthScreen.Login.route,
    ){
        composable(
            route = AuthScreen.Login.route
        ){
            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state.collectAsState()
            val uiEvent = viewModel.eventFlow.collectAsState(null)


            LoginScreen(
                state = state.value,
                onRequestOtp = viewModel::requestOtp,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
                onSignUpClick = {
                    navController.navigate(
                        route = AuthScreen.Register.route
                    )
                },
                onOtpSent = {
                    navController.navigate(
                        route = AuthScreen.VerifyOtp.passPhone(state.value.phone.value)
                    )
                },
                event = uiEvent.value,
                snackbarHostState = snackbarHostState,
                paddingValues = paddingValues
            )
        }

        composable(
            route = AuthScreen.Register.route
        ){
            val viewModel = hiltViewModel<ScreenViewModel>()
            val state = viewModel.state.collectAsState()
            val uiEvent = viewModel.event.collectAsState(null)

            RegisterScreen(
                state = state.value,
                event = uiEvent.value,
                onFullNameChange = viewModel::onFullNameChange,
                onPhoneChange = viewModel::onPhoneNumberChange,
                onLoginClick = {
                    navController.popBackStack()
                },
                onRegister = viewModel::onRegister,
                snackbarHostState = snackbarHostState,
                onNavigateToVerifyOtpScreen = {
                    navController.navigate(
                        route = AuthScreen.VerifyOtp.passPhone(state.value.phoneNumber.value)
                    )
                }
            )
        }

        composable(
            route = AuthScreen.VerifyOtp.route
        ){
            val phone = it.arguments?.getString("phone")
            val viewModel = hiltViewModel<VerifyOtpScreenViewModel>()
            val state = viewModel.state.collectAsState().value
            val event = viewModel.event.collectAsState(null).value
            VerifyOtpScreen(
                state = state,
                onOtpChange = viewModel::onOtpChange,
                event = event,
                onVerify = viewModel::verifyOtp,
                onNavigateToHomeScreen = {
                    navController.navigate(
                        route = ImaanApp.Home.route
                    ){
                        popUpTo(
                            AUTH_FEATURE
                        ){
                            inclusive = true
                        }
                    }
                },
                onResendOtp = {
                    phone?.let { phoneNumber ->
                        viewModel.resendOtp(phoneNumber)
                    }
                }
            )
        }
    }
}