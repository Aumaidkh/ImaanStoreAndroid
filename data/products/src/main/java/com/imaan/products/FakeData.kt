package com.imaan.products

import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Stocks
import com.imaan.common.model.Title
import java.net.URL
import java.util.UUID


val dummyProduct = ProductModel(
    id = ID(UUID.randomUUID().toString()),
    imageUrl = URL("https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
    title = Title("Sony A-6000 Mirrorless Camera"),
    description = Description("Captures and records visual images and videos. It is widely used for various purposes, including photography, videography, surveillance, broadcasting, and more. Cameras have evolved significantly over the years, with advancements in technology leading to improvements in image quality, portability, and functionality. "),
    price = Amount(100.0),
    stocks = Stocks(120),
    discount = Discount(0.3f)
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