package com.imaan.store.core.domain.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.imaan.store.feature_auth.presentation.register.PhoneNumber
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Address Model*/
@Serializable
data class Address(
    val id: AddressId? = null,
    val userId: UserId,
    val fullName: FullName,
    val landMark: LandMark? = null,
    val city: City,
    val district: District,
    val state: State,
    val country: Country,
    val pinCode: PinCode,
    val phoneNumber: PhoneNumber,
    val isDefault: Boolean,
    val fullAddress: FullAddress
){
    val readableAddress
        get() = buildAddress()

    fun toJson(): String {
        return Json.encodeToString(this)
    }

    private fun buildAddress(separator: Char = ','): String {
        return buildAnnotatedString {
            appendWithSeparator(fullAddress.value,separator)
            landMark?.let {
                appendWithSeparator("Near ${it.value}",separator)
            }
            appendWithSeparator(city.value,separator)
            appendWithSeparator(district.value,separator)
            appendWithSeparator(state.value,separator)
            appendWithSeparator(country.value,separator)
            appendWithSeparator(pinCode.value,separator)
        }.text
    }

    private fun AnnotatedString.Builder.appendWithSeparator(value: String,separator: Char){
        if (value.isEmpty()){
            return
        }
        append("$value$separator ")
    }
    companion object {
        fun String.decodeFromString(): Address? {
            return Json.decodeFromString<Address?>(this)
        }

        val BLANK_ADDRESS = Address(
            userId = UserId(""),
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

@Serializable
@JvmInline
value class FullAddress(val value: String){
    companion object{
        val Empty = FullAddress("")
    }
}
@Serializable
@JvmInline
value class PinCode(val value: String) {
    companion object {
        val Empty = PinCode("")
    }
}

@Serializable
@JvmInline
value class City(val value: String) {
    companion object {
        val Empty = City("")
    }
}

@Serializable
@JvmInline
value class District(val value: String){
    companion object {
        val Empty = District("")
    }
}

@Serializable
@JvmInline
value class State(val value: String) {
    companion object {
        val Empty = State("")
    }
}

@Serializable
@JvmInline
value class Country(val value: String){
    companion object {
        val Empty = Country("")
    }
}

@Serializable
@JvmInline
value class LandMark(val value: String){
    companion object {
        val Empty = LandMark("")
    }
}

@Serializable
data class FullName(val value: String,val error: String?){
    companion object {
        val Empty = FullName("",null)
    }
}


@Serializable
@JvmInline
value class AddressId(val value: String)

fun dummyAddress(key: String = ""): Address {
    return Address(
        id = AddressId(key),
        userId = UserId.getUserId(),
        fullName = FullName("Murtaza Khursheed",null),
        city = City("Anantnag"),
        landMark = LandMark("Bridge"),
        district = District("Anantnag"),
        state = State("J&K"),
        country = Country("India"),
        phoneNumber = PhoneNumber("128717271"),
        pinCode = PinCode("192101"),
        isDefault = false,
        fullAddress = FullAddress.Empty
    )
}

fun dummyAddresses(count: Int = 4): List<Address>{
    val list = mutableListOf<Address>()
    repeat(count){
        list.add(dummyAddress(key = "$it"))
    }
    return list
}