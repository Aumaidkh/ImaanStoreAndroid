package com.imaan.store.feature_manage_addresses.presentation.screen.add

import com.imaan.store.core.domain.model.City
import com.imaan.store.core.domain.model.Country
import com.imaan.store.core.domain.model.District
import com.imaan.store.core.domain.model.FullAddress
import com.imaan.store.core.domain.model.FullName
import com.imaan.store.core.domain.model.LandMark
import com.imaan.store.core.domain.model.PinCode
import com.imaan.store.core.domain.model.State
import com.imaan.store.feature_auth.presentation.register.PhoneNumber

data class UpsertAddressScreenUiState(
    val loading: Boolean = false,
    val fullName: FullName = FullName.Empty,
    val landmark: LandMark? = null,
    val city: City = City.Empty,
    val district: District = District.Empty,
    val state: State = State.Empty,
    val country: Country = Country.Empty,
    val pin: PinCode = PinCode.Empty,
    val phone: PhoneNumber = PhoneNumber.Empty,
    val fullAddress: FullAddress = FullAddress.Empty
)
