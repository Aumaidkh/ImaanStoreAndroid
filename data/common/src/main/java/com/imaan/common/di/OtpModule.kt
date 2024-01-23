package com.imaan.common.di

import com.imaan.common.repository.IOtpRepository
import com.imaan.common.repository.OtpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class OtpModule {

    @Binds
    @Singleton
    internal abstract fun bindsOtpRepository(impl: OtpRepositoryImpl): IOtpRepository

}