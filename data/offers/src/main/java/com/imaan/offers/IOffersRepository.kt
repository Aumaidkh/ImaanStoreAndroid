package com.imaan.offers

interface IOffersRepository {
    suspend fun fetchAllOffers(): List<OfferModel>
}