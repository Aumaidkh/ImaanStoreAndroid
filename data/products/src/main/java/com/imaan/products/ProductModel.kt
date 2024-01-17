package com.imaan.products

import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Title
import com.imaan.common.model.Stocks
import java.net.URL


data class ProductModel(
    val id: ID,
    val imageUrl: URL,
    val title: Title,
    val description: Description,
    val price: Amount,
    val stocks: Stocks,
    val discount: Discount?
)
