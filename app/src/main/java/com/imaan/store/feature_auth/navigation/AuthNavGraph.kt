package com.imaan.store.feature_auth.navigation

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
import com.imaan.store.feature_auth.presentation.verify_otp.VerifyOtpScreen

fun NavGraphBuilder.authNavigation(
    navController: NavHostController
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
            val phone = viewModel.phone.collectAsState()
            LoginScreen(
                state = state.value,
                onRequestOtp = viewModel::login,
                phone = phone.value,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
                onSignUpClick = {
                    navController.navigate(
                        route = AuthScreen.Register.route
                    )
                },
                onOtpSent = {
                    navController.navigate(
                        route = AuthScreen.VerifyOtp.passOtp(it.value.toString())
                    )
                }
            )
        }

        composable(
            route = AuthScreen.Register.route
        ){
            RegisterScreen()
        }

        composable(
            route = AuthScreen.VerifyOtp.route
        ){
            val otp = it.arguments?.getString("otp")
            VerifyOtpScreen(

            )
        }
    }
}