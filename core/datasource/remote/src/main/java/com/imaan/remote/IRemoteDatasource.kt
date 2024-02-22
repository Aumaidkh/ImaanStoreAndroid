package com.imaan.remote

import com.imaan.remote.dto.Product
import com.imaan.util.Result
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow

interface IRemoteDatasource {
    suspend fun fetchProducts(): Flow<Result<List<Product>>>

    suspend fun insertProduct()
}