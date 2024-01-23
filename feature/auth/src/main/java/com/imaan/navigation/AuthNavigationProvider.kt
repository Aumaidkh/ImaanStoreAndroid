package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.auth.register.RegisterScreen
import com.imaan.auth.register.RegisterScreenViewModel
import com.imaan.auth.signin.LoginScreen
import com.imaan.auth.signin.LoginViewModel
import com.imaan.auth.verify_otp.VerifyOtpScreen
import com.imaan.auth.verify_otp.VerifyOtpScreenViewModel
import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber

fun NavGraphBuilder.authNavigationProvider(
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues = PaddingValues(),
    onSignInClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onOtpSent: (OTP) -> Unit = {},
    onAuthenticationSucceeded: () -> Unit = {},
    onRegistrationSucceeded: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    navigation(
        route = AuthRoute.Feature,
        startDestination = AuthRoute.SignIn.route,
    ) {
        composable(
            route = AuthRoute.SignIn.route
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state.collectAsState()

            LaunchedEffect(
                key1 = state.value.message,
                key2 = state.value.otp
            ) {
                state.value.otp?.let {
                    onOtpSent(it)
                } ?: state.value.message?.let {
                    snackbarHostState.showSnackbar(
                        message = it
                    )
                }
            }

            LoginScreen(
                state = state.value,
                onRequestOtp = viewModel::requestOtp,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
                onSignUpClick = onRegisterClick,
                paddingValues = paddingValues
            )
        }

        composable(
            route = AuthRoute.Register.route
        ) {
            val viewModel = hiltViewModel<RegisterScreenViewModel>()
            val state = viewModel.state.collectAsState()

            LaunchedEffect(
                key1 = state.value.error,
                key2 = state.value.otp
            ) {
                state.value.error?.let {
                    snackbarHostState.showSnackbar(it)
                    viewModel.onErrorShown()
                } ?: state.value.otp?.let {
                    onRegistrationSucceeded()
                    viewModel.otpConsumed()
                }
            }

            RegisterScreen(
                state = state.value,
                onFullNameChange = viewModel::onFullNameChange,
                onPhoneChange = viewModel::onPhoneNumberChange,
                onLoginClick = onBack,
                onRegister = viewModel::onRegister,
                paddingValues = paddingValues
            )
        }

        composable(
            route = AuthRoute.VerifyOtp.route
        ) {
            val phone = it.arguments?.getString("phone")
            val viewModel = hiltViewModel<VerifyOtpScreenViewModel>()
            val state = viewModel.state.collectAsState().value


            LaunchedEffect(
                key1 = state.errorMessage,
                key2 = state.authenticated
            ) {
                state.errorMessage?.let { message ->
                    snackbarHostState.showSnackbar(message)
                    viewModel.onErrorShown()
                }
                if (state.authenticated){
                    onAuthenticationSucceeded()
                }
            }
            VerifyOtpScreen(
                state = state,
                onOtpChange = viewModel::onOtpChange,
                onVerify = viewModel::verifyOtp,
                onResendOtp = {
                    phone?.let { phoneNumber ->
                        viewModel.requestOtpOn(PhoneNumber(phoneNumber))
                    }
                }
            )
        }
    }
}