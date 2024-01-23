package com.imaan.addresses.add_addresses

import com.imaan.addresses.Address
import com.imaan.common.model.City
import com.imaan.common.model.Country
import com.imaan.common.model.District
import com.imaan.common.model.FullAddress
import com.imaan.common.model.FullName
import com.imaan.common.model.ID
import com.imaan.common.model.Landmark
import com.imaan.common.model.PhoneNumber
import com.imaan.common.model.PinCode
import com.imaan.common.model.State

enum class AddressOperation {
    Saved
}

data class UpsertAddressScreenUiState(
    val loading: Boolean = false,
    val fullName: FullName = FullName.Empty,
    val landmark: Landmark? = null,
    val city: City = City.Empty,
    val district: District = District.Empty,
    val state: State = State.Empty,
    val country: Country = Country.Empty,
    val pin: PinCode = PinCode.Empty,
    val phone: PhoneNumber = PhoneNumber.Empty,
    val fullAddress: FullAddress = FullAddress.Empty,
    val addressProcessed: Boolean = false
){

    val address get() = Address(
        id = null,
        userId = ID(""),
        fullName = fullName,
        landMark = landmark,
        city = city,
        district = district,
        state = state,
        country = country,
        pinCode = pin,
        phoneNumber = phone,
        isDefault = false,
        fullAddress = fullAddress
    )
}