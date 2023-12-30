package com.imaan.store.feature_auth.di

import com.imaan.store.feature_auth.data.repository.AuthRepositoryImpl
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
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
    fun bindsAuthRepository(repo: AuthRepositoryImpl): IAuthRepository
}