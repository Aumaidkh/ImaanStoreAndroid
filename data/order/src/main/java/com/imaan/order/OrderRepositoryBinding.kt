package com.imaan.order

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRepositoryBinding {

    @Singleton
    @Binds
    internal abstract fun bindRepository(impl: OrderRepositoryImpl): IOrderRepository
}