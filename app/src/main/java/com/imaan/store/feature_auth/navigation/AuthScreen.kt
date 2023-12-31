package com.imaan.store.feature_auth.navigation

import com.imaan.store.feature_auth.navigation.NavigationConstants.LOGIN_ROUTE
import com.imaan.store.feature_auth.navigation.NavigationConstants.REGISTER_ROUTE
import com.imaan.store.feature_auth.navigation.NavigationConstants.VERIFY_OTP

sealed class AuthScreen(val route: String){
    object Login: AuthScreen(LOGIN_ROUTE)
    object Register: AuthScreen(REGISTER_ROUTE)

    object VerifyOtp: AuthScreen("$VERIFY_OTP/{otp}"){
        fun passOtp(otp: String): String{
            return "${VERIFY_OTP}/$otp"
        }
    }
}
