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

val dummyUrl = URL("https://png2.cleanpng.com/sh/9d873a0a31c34099f09fefaa11493458/L0KzQYm3V8A4N5lBkJH0aYP2gLBuTgNwdqoyTqI5MD3wecP5jCJtbaR4Rdt3dHX1c7nojvdmaZNxfZ91ZX72PbTojfVzaV54h9DELXG5QIG3TcVjPGI6e9RtZXPkQYKATsE0P2I6Tqc6MUW2QYG4UMI0P2o7UaQ3cH7q/kisspng-sony-6000-mirrorless-interchangeable-lens-camera-sony-a6000-5b415cbdeca117.1371565115310102379692.png")

val dummyProduct = ProductModel(
    id = ID(UUID.randomUUID().toString()),
    imageUrl = URL("https://png2.cleanpng.com/sh/9d873a0a31c34099f09fefaa11493458/L0KzQYm3V8A4N5lBkJH0aYP2gLBuTgNwdqoyTqI5MD3wecP5jCJtbaR4Rdt3dHX1c7nojvdmaZNxfZ91ZX72PbTojfVzaV54h9DELXG5QIG3TcVjPGI6e9RtZXPkQYKATsE0P2I6Tqc6MUW2QYG4UMI0P2o7UaQ3cH7q/kisspng-sony-6000-mirrorless-interchangeable-lens-camera-sony-a6000-5b415cbdeca117.1371565115310102379692.png"),
    title = Title("Sony A-6000 Mirrorless Camera | Night Photography"),
    description = Description("Captures and records visual images and videos. It is widely used for various purposes, including photography, videography, surveillance, broadcasting, and more. Cameras have evolved significantly over the years, with advancements in technology leading to improvements in image quality, portability, and functionality. "),
    price = Amount(100.0),
    stocks = Stocks(120),
    discount = Discount(0.3f),
    images = listOf(
        Image(
            thumbnail = dummyUrl,
            original = dummyUrl
        ),
        Image(
            thumbnail = dummyUrl,
            original = dummyUrl
        ),
        Image(
            thumbnail = dummyUrl,
            original = dummyUrl
        ),
        Image(
            thumbnail = dummyUrl,
            original = dummyUrl
        ),
        Image(
            thumbnail = dummyUrl,
            original = dummyUrl
        ),
    ),
    colors = getDummyColors(),
    sizes = getDummySizes(),
    customVariants = getDummyVariants()
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