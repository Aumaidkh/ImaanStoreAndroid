package com.imaan.store.core.domain.model

import java.util.UUID

data class ProductModel(
    val id: String,
    val imageUrl: String,
    val name: String,
    val description: String,
    val price: Amount,
    val stocks: Int,
    val discount: Float
)

val dummyProduct = ProductModel(
    id = UUID.randomUUID().toString(),
    imageUrl = "https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    name = "Sony A-6000 Mirrorless Camera",
    description = "Captures and records visual images and videos. It is widely used for various purposes, including photography, videography, surveillance, broadcasting, and more. Cameras have evolved significantly over the years, with advancements in technology leading to improvements in image quality, portability, and functionality. ",
    price = Amount(100.0),
    stocks = 100,
    discount = 0.3f
)

fun getDummyProducts(count: Int = 1): List<ProductModel>{
    val products = mutableListOf<ProductModel>()
    repeat(count){
        products.add(dummyProduct.copy(
            id = UUID.randomUUID().toString()
        ))
    }
    return products
}
