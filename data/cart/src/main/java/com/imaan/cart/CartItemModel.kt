package com.imaan.cart

import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Title
import com.imaan.products.ProductModel
import com.imaan.products.getDummyProducts
import com.imaan.products.model.IProductModel
import com.imaan.remote.dto.Cart
import com.imaan.remote.dto.EmbeddedCartProduct
import com.imaan.remote.dto.Inventory
import com.imaan.remote.dto.ProductVariant
import org.mongodb.kbson.BsonObjectId
import java.net.URL

data class CartItemModel(
    val id: ID? = null,
    val productModel: IProductModel? = getDummyProducts(1).first(),
    var quantity: Int = 1,
) {
    constructor(
        cart: Cart
    ): this(
        id = ID(cart._id.toHexString()),
        productModel = cart.product?.let { ProductModel(it) },
        quantity = cart.quantity
    )
    constructor(
        inventory: Inventory,
        variant: ProductVariant,
        quantity: Int
    ): this(
        productModel = ProductModel(
            inventory = inventory,
            variant = variant
        ),
        quantity = quantity
    )
    val totalAmount get() = productModel?.price?.multiply(quantity)
    val productNameWithQuantity get() = "${productModel?.title?.value} X $quantity"
    val itemQuantity get() = quantity

    val totalPrice: Amount
        get() =
        (productModel?.price ?: Amount.ZERO) multiply quantity

    fun toCart(initialQuantity: Int = 1): Cart {
        return Cart().apply {
            productModel?.id?.value?.let {
                this.variantId = org.mongodb.kbson.ObjectId(it)
            }
            this.quantity = initialQuantity
        }
    }

}