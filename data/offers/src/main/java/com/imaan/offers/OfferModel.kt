package com.imaan.offers

import com.imaan.common.model.Color
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Title
import java.net.URL
import java.util.Date


data class OfferModel(
    val id: ID,
    val imageUrl: URL,
    val startDate: Date,
    val expiryDate: Date,
    val discount: Discount,
    val discountColor: Color = Color.White,
    val title: Title,
    val titleColor: Color = Color.White,
    val description: Description,
    val descriptionColor: Color = Color.White
)