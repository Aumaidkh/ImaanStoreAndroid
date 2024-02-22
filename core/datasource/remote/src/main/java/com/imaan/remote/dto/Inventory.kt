package com.imaan.remote.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Inventory: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    @Index
    var productId: ObjectId = ObjectId()
    @Index
    var variantId: ObjectId = ObjectId()
    var stocks: Int = 0
    var price: Double = 0.0
    var discount: Double = 0.0
}