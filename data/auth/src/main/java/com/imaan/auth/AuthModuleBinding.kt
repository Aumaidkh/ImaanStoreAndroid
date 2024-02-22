package com.imaan.auth

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.mongodb.App
import javax.inject.Singleton

const val APP_ID = "devicesync-rklkd"
@Module
@InstallIn(SingletonComponent::class)
object AuthModuleBinding {

    @Provides
    @Singleton
    fun provideMongoApp(): App {
        return App.create(APP_ID)
    }
    @Provides
    @Singleton
    fun bindAuthService(app: App): IAuthService{
        return MongoAuthService(app)
    }
}