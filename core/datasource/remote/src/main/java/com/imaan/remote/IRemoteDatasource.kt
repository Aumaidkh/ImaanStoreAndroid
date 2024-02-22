package com.imaan.remote

import com.imaan.remote.dto.Inventory
import com.imaan.remote.dto.Product
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow

interface IRemoteDatasource {
    suspend fun fetchProducts(): Flow<Result<List<Product>>>

    suspend fun fetchInventories(): Flow<Result<List<Inventory>>>

    suspend fun insertProduct()
}