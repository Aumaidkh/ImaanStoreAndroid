package com.imaan.cart

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartRepositoryBinding {

    @Singleton
    @Binds
    internal abstract fun bindCartRepository(impl: CartRepositoryImpl): ICartRepository

}

@Singleton
object CartDatasource {
    var cartItems = mutableListOf<CartItemModel>()
}