package com.imaan.remote.dto

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Cart: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    @Index
    var userId: ObjectId = ObjectId()
    var variantId: ObjectId = ObjectId()
    var quantity: Int = 0
    var product: EmbeddedCartProduct? = null
}

class EmbeddedCartProduct: EmbeddedRealmObject {
    var price: Double = 0.0
    var imageUrl: String? = null
    var stocks: Int = 0
    var name: String = ""
    var description: String = ""
    var variantId: ObjectId = ObjectId()
    var discount: Double = 0.0
}