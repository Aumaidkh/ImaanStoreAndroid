package com.imaan.remote.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Address: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var label: String = ""
    var userId: ObjectId = ObjectId()
    var fullName: String = ""
    var fullAddress: String = ""
    var phoneNumber: String = ""
    var landmark: String? = null
    var district: String = ""
    var state: String = ""
    var country: String = ""
    var zipCode: String = ""
    var alternatePhone: Long? = null
}