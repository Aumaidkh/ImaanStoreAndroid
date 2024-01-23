package com.imaan.navigation

import com.imaan.util.NavigationRoute

sealed interface Profile: NavigationRoute {
    object ViewProfileRoute: Profile {
        override val route: String
            get() = "view-profile"
    }

    object EditProfile: Profile {
        override val route: String
            get() = "edit-profile"
    }

    companion object {
        const val feature = "profile-featur"
    }
}