package com.imaan.store.feature_manage_addresses.navigation

import com.imaan.store.core.domain.model.Address
import com.imaan.store.feature_manage_addresses.presentation.screen.add.ADDRESS_KEY
import com.imaan.store.navigation.Route

const val ManageAddresses = "manage-addresses"
sealed class AddressesRoute(override val route: String): Route {
    object PickAddress: AddressesRoute("view-addresses")
    object AddNewAddress: AddressesRoute("add-address/{$ADDRESS_KEY}"){
        fun passAddress(address: Address): String{
            return "add-address/${address.toJson()}"
        }
    }
}