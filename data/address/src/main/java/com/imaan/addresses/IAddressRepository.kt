package com.imaan.addresses

import com.imaan.common.model.ID
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {

    suspend fun getAllSavedAddressesAsFlow(): Flow<List<Address>>
    suspend fun getAllSavedAddresses(): List<Address>

    suspend fun getAddressById(id: ID): Address?

    suspend fun upsertAddress(address: Address)
}