package com.imaan.remote.dto

import io.realm.kotlin.types.EmbeddedRealmObject
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

class EmbeddedInventory: EmbeddedRealmObject{
    @Index
    var productId: ObjectId = ObjectId()
    @Index
    var variantId: ObjectId = ObjectId()
    var stocks: Int = 0
    var price: Double = 0.0
    var discount: Double = 0.0
}

fun EmbeddedInventory.toInventory(): Inventory {
    val inventory = Inventory()
    with(this){
        inventory.discount = this.discount
        inventory.productId = this.productId
        inventory.price = this.price
        inventory.stocks= this.stocks
        inventory.variantId = this.variantId
    }
    return inventory
}