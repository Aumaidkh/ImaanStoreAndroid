package com.imaan.addresses

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
import com.imaan.common.model.Title


/**
 * Address Model*/

data class Address(
    val id: ID? = null,
    val title: Title = Title("Office"),
    val userId: ID,
    val fullName: FullName,
    val landMark: Landmark? = null,
    val city: City,
    val district: District,
    val state: State,
    val country: Country,
    val pinCode: PinCode,
    val phoneNumber: PhoneNumber,
    val isDefault: Boolean,
    val fullAddress: FullAddress,
){
    val readableAddress
        get() = buildAddress()


    private fun buildAddress(separator: Char = ','): String {
        return buildString {
            appendWithSeparator(fullAddress.value,separator)
            landMark?.let {
                appendWithSeparator("Near ${it.value}",separator)
            }
            appendWithSeparator(city.value,separator)
            appendWithSeparator(district.value,separator)
            appendWithSeparator(state.value,separator)
            appendWithSeparator(country.value,separator)
            appendWithSeparator(pinCode.value,separator)
        }
    }

    private fun StringBuilder.appendWithSeparator(value: String,separator: Char){
        if (value.isEmpty()){
            return
        }
        append("$value$separator ")
    }
    companion object {

        val BLANK_ADDRESS = Address(
            userId = ID(""),
            fullName = FullName.Empty,
            landMark = null,
            city = City.Empty,
            district = District.Empty,
            state = State.Empty,
            country = Country.Empty,
            pinCode = PinCode.Empty,
            phoneNumber = PhoneNumber.Empty,
            isDefault = false,
            fullAddress = FullAddress.Empty
        )
    }
}






