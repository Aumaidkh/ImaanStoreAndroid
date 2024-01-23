package com.imaan.products

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModuleBinding {

    @Binds
    internal abstract fun bindRepository(impl: ProductRepositoryImpl): IProductRepository
}