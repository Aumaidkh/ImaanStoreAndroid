package com.imaan.navigation

import com.imaan.util.NavigationRoute

sealed interface Payment: NavigationRoute {

    object SelectPaymentMethod: Payment {
        override val route: String
            get() = "payment-method"
    }

    object Confirmation: Payment {
        override val route: String
            get() = "payment-confirmation"
    }



    companion object {
        const val feature = "payment"
    }
}
