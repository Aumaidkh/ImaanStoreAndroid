package com.imaan.cart

import com.imaan.common.model.Amount
import com.imaan.products.ProductModel
import com.imaan.products.getDummyProducts

data class CartItemModel(
    val productModel: ProductModel = getDummyProducts(1).first(),
    var quantity: Int = 1
) {
    val itemQuantity get() = quantity

    val totalPrice: Amount
        get() =
        productModel.price multiply quantity

}