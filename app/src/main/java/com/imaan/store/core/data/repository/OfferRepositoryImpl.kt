package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.OfferRepository
import com.imaan.store.feature_home.presentation.composables.OfferModel
import com.imaan.store.feature_home.presentation.composables.getDummyOffer
import kotlinx.coroutines.delay

class OfferRepositoryImpl: OfferRepository {

    override suspend fun fetchAllOffers(): List<OfferModel> {
    //   delay(2000)
        return getDummyOffer(5)
    }
}