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
import org.mongodb.kbson.BsonObjectId


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
    val alternatePhone: PhoneNumber? = null
){
    constructor(address: com.imaan.remote.dto.Address): this(
        id = ID(address._id.toHexString()),
        userId = ID(address._id.toHexString()),
        fullName = FullName(address.fullName,null),
        landMark = address.landmark?.let { Landmark(it) },
        city = City("No City"),
        district = District(address.district),
        state = State(address.state),
        country = Country(address.country),
        pinCode = PinCode(address.zipCode),
        phoneNumber = PhoneNumber(address.phoneNumber),
        isDefault = false,
        fullAddress = FullAddress(address.fullAddress),
        alternatePhone = PhoneNumber(address.alternatePhone.toString(),"")
    )
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

    fun toAddressDto(): com.imaan.remote.dto.Address {
        return com.imaan.remote.dto.Address().apply {
            this@Address.id?.let { addressId ->
                this._id = org.mongodb.kbson.ObjectId(addressId.value)
            }
            this.fullName = this@Address.fullName.value
            this.label = this@Address.fullName.value
            this.country = this@Address.country.value
            this.state = this@Address.state.value
            this.district = this@Address.district.value
            this.zipCode = this@Address.pinCode.value
            this.phoneNumber = this@Address.phoneNumber.value
            this.fullAddress = this@Address.fullAddress.value
            this.landmark = this@Address.landMark?.value
            this.alternatePhone = null
        }
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






