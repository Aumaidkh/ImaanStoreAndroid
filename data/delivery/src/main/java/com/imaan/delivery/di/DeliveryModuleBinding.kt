package com.imaan.delivery.di

import com.imaan.delivery.repository.DeliveryServiceImpl
import com.imaan.delivery.repository.IDeliveryService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DeliveryModuleBinding {

    @Singleton
    @Binds
    internal abstract fun bindDeliveryService(impl: DeliveryServiceImpl): IDeliveryService
}