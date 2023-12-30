package com.imaan.store.feature_auth.di

import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
interface TestRepositoryModule {
    @Singleton
    @Binds
    fun bindsFakeAuthRepository(repo: FakeAuthRepository): IAuthRepository
}