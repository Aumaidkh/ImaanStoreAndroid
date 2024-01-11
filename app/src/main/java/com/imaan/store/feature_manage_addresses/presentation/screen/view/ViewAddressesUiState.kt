package com.imaan.store.feature_manage_addresses.presentation.screen.view

import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.domain.model.dummyAddresses

data class ViewAddressesUiState(
    val addresses: List<Address> = dummyAddresses(5),
    val loading: Boolean = false,
    val selectedAddress: Address? = null,
)
