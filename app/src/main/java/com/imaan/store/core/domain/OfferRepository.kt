package com.imaan.store.core.domain

import com.imaan.store.feature_home.presentation.composables.OfferModel

interface OfferRepository {
    suspend fun fetchAllOffers(): List<OfferModel>
}