package com.imaan.products

import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Stocks
import com.imaan.common.model.Title
import java.net.URL
import java.util.UUID

val dummyUrl = URL("https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/%E2%80%94Pngtree%E2%80%94dslr%20digital%20camera_8231559%20(1).png?alt=media&token=6de35308-1623-4f5d-bfa7-b6eb2c9323f4")

val dummyProduct = ProductModel(
    id = ID(UUID.randomUUID().toString()),
     title = Title("Sony A-6000 Mirrorless Camera | Night Photography"),
    description = Description("Captures and records visual images and videos. It is widely used for various purposes, including photography, videography, surveillance, broadcasting, and more. Cameras have evolved significantly over the years, with advancements in technology leading to improvements in image quality, portability, and functionality. "),
    price = Amount(100.0),
    stocks = Stocks(120),
    discount = Discount(0.3f),
    category = "Electronics",
    image = Image(
        thumbnail = dummyUrl,
        original = dummyUrl
    )
)

fun getDummyProducts(count: Int = 1): List<ProductModel>{
    val products = mutableListOf<ProductModel>()
    repeat(count){
        products.add(dummyProduct.copy(
            id = ID(UUID.randomUUID().toString())
        ))
    }
    return products
}