package com.imaan.common.di

import com.imaan.common.usecase.FullNameValidatorImpl
import com.imaan.common.usecase.IFullNameValidator
import com.imaan.common.usecase.IPhoneNumberValidator
import com.imaan.common.usecase.PhoneNumberValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidationModule {

    @Binds
    @Singleton
    internal abstract fun bindsFullNameValidator(impl: FullNameValidatorImpl): IFullNameValidator

    @Binds
    @Singleton
    internal abstract fun bindsPhoneNumberValidator(impl: PhoneNumberValidatorImpl): IPhoneNumberValidator
}