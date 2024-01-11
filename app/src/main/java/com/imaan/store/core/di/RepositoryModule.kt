package com.imaan.store.core.di

import com.imaan.store.core.data.repository.OrderRepositoryImpl
import com.imaan.store.core.domain.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository
}