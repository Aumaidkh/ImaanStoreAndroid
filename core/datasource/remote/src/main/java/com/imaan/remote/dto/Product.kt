package com.imaan.remote.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Product : RealmObject{
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var description: String = ""
    var thumbnailUrl: String = ""
}