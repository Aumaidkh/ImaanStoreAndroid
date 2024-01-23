package com.imaan.addresses.view_addresses

import com.imaan.addresses.Address
import com.imaan.addresses.dummyAddresses

data class ViewAddressesUiState(
    val addresses: List<Address> = dummyAddresses(5),
    val loading: Boolean = false,
    val selectedAddress: Address? = null,
    val deliveryAddress: Address? = null,
    val errorMessage: String? = null,
)