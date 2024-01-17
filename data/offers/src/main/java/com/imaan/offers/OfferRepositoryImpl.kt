package com.imaan.offers

import javax.inject.Inject

internal class OfferRepositoryImpl @Inject constructor(): IOffersRepository {
    override suspend fun fetchAllOffers(): List<OfferModel> {
        return getDummyOffer(6)
    }
}