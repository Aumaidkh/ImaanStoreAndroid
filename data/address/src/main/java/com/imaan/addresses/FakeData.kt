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


fun dummyAddress(key: String = ""): Address {
    return Address(
        id = ID(key),
        userId = ID(""),
        fullName = FullName("Murtaza Khursheed",null),
        city = City("Anantnag"),
        landMark = Landmark("Bridge"),
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