package com.imaan.user

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModuleBinding {

    @Binds
   internal abstract fun bindsRepository(impl: UserRepositoryImpl): IUserRepository
}