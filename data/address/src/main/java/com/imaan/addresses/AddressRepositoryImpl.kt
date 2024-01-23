package com.imaan.addresses

import com.imaan.common.model.ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class AddressRepositoryImpl @Inject constructor(): IAddressRepository {

    private val addresses = mutableListOf<Address>()
    private val addressesFlow = flowOf(mutableListOf<Address>())

    init {
        addresses.addAll(dummyAddresses(2))
    }

    override suspend fun getAllSavedAddressesAsFlow(): Flow<List<Address>> {
        return addressesFlow
    }

    override suspend fun getAllSavedAddresses(): List<Address> {
        return addresses
    }

    override suspend fun getAddressById(id: ID): Address? {
        return addresses.find { it.id == id }
    }

    override suspend fun upsertAddress(address: Address) {
        addresses.add(address)
    }
}