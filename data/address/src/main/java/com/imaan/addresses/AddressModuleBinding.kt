package com.imaan.addresses

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AddressModuleBinding {

    @Singleton
    @Binds
    internal abstract fun bindsAddressRepository(impl: AddressRepositoryImpl): IAddressRepository
}