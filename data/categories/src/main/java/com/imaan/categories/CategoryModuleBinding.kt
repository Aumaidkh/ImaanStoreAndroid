package com.imaan.categories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryDataModuleBinding {
    @Binds
    @Singleton
    internal abstract fun bindsCategoryRepository(impl: CategoryRepositoryImpl): ICategoryRepository
}