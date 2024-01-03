package com.imaan.store.core.di

import com.imaan.store.core.domain.usecase.validation.FullNameValidator
import com.imaan.store.core.domain.usecase.validation.PhoneNumberValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    @Singleton
    @Provides
    fun providesPhoneNumberValidator(): PhoneNumberValidator {
        return PhoneNumberValidator()
    }

    @Singleton
    @Provides
    fun providesFullNameValidator(): FullNameValidator {
        return FullNameValidator()
    }
}