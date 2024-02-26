package com.imaan.navigation

import com.imaan.common.model.ID
import com.imaan.util.NavigationRoute

const val ManageAddressesFeature = "feature_addresses"

internal const val AddressId = "addressId"
sealed interface ManageAddresses : NavigationRoute {

    object SavedAddresses: ManageAddresses {
        override val route: String
            get() = "addresses"
    }

    object UpsertAddress: ManageAddresses {

        private const val baseRoute = "add-address"
        override val route: String
            get() = "$baseRoute/{$AddressId}"

        fun passAddressId(id: ID): String {
            return route.replace("{$AddressId}",id.value)
        }
    }
}