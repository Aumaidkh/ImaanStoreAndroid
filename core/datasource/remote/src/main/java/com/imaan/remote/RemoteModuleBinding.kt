package com.imaan.remote

import com.imaan.auth.IAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.mongodb.App
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModuleBinding {


    @Provides
    @Singleton
    fun providesRemoteDatasource(
        app: App,
        authService: IAuthService
    ): IRemoteDatasource {
        return MongoDatasource(app, authService)
    }
}