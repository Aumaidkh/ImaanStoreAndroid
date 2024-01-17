package com.imaan.offers

import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Title
import java.net.URL
import java.util.Date


fun getDummyOffer(count: Int = 1): List<OfferModel> {
    val offers = mutableListOf<OfferModel>()
    repeat(count) {
        offers.add(
            OfferModel(
                id = ID(""),
                imageUrl = URL("https://images.pexels.com/photos/1279813/pexels-photo-1279813.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                startDate = Date(),
                expiryDate = Date(),
                discount = Discount(0.50f),
                title = Title("On all PRADA product"),
                description = Description("Promo code WD400 to get 25% off on all PRADA product")
            )
        )
    }

    return offers
}