package com.imaan.addresses

import com.imaan.common.model.ID
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {

    suspend fun getAllSavedAddressesAsFlow(): Flow<Result<List<Address>>>
    suspend fun getAllSavedAddresses(): List<Address>

    suspend fun getAddressById(id: ID): Address?
    suspend fun getAddressByIdAsFlow(id: ID): Flow<Result<Address>>

    suspend fun upsertAddress(address: Address)
    suspend fun upsertAddressWithResultFlow(address: Address): Flow<Result<Address>>
}