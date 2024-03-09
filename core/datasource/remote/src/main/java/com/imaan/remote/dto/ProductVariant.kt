package com.imaan.remote.dto

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class ProductVariant: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    @Index
    var productId: ObjectId = ObjectId()
    var name: String = ""
    var label: String = ""
    var hexColor: String? = null
    var size: String? = null
    var images: RealmList<String> = realmListOf()
    var specs: String = ""
}

fun EmbeddedProductVariant.toProductVariant(): ProductVariant {
    val variant = ProductVariant()
    with(this){
        variant.productId = this.productId
        variant.specs = this.specs
        variant.images = this.images
        variant.size = this.size
        variant.label = this.label
        variant.hexColor = this.hexColor
        variant.name = this.name
    }
    return variant
}

class EmbeddedProductVariant:EmbeddedRealmObject{
    @Index
    var productId: ObjectId = ObjectId()
    var name: String = ""
    var label: String = ""
    var hexColor: String? = null
    var size: String? = null
    var images: RealmList<String> = realmListOf()
    var specs: String = ""
}