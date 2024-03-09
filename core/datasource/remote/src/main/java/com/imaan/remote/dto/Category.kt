package com.imaan.remote.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Category: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var label: String = ""
    var image: String = ""
}