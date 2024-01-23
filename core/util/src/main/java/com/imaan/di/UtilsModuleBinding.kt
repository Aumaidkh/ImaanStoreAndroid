package com.imaan.di

import com.imaan.util.IDispatcherProvider
import com.imaan.util.ImaanAppDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModuleBinding {

    @Singleton
    @Binds
    internal abstract fun bindsDispatchers(impl: ImaanAppDispatchers): IDispatcherProvider
}