package com.imaan.navigation

import com.imaan.util.NavigationRoute

interface AuthRoute: NavigationRoute {

    companion object {
        const val Feature = "auth-feature"
    }
    object Register: NavigationRoute {
        override val route: String
            get() = "register"
    }

    object SignIn: NavigationRoute {
        override val route: String
            get() = "sign-in"
    }

    object VerifyOtp: NavigationRoute {
        override val route: String
            get() = "verify-otp"
    }
}