package com.imaan.offers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OfferModuleBinding {

    @Binds
    internal abstract fun bindsOfferRepository(impl: OfferRepositoryImpl): IOffersRepository
}