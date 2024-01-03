package com.imaan.store.core.di

import com.imaan.store.core.domain.DispatcherProvider
import com.imaan.store.core.utils.DefaultDispatchers
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Singleton
    fun bindsDefaultDispatchers(): DispatcherProvider{
        return DefaultDispatchers(
            ioDispatcher = Dispatchers.IO,
            mainDispatcher = Dispatchers.Main,
            defaultDispatcher = Dispatchers.Default,
        )
    }


}