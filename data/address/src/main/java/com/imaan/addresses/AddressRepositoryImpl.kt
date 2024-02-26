package com.imaan.addresses

import com.imaan.common.model.ID
import com.imaan.remote.IRemoteDatasource
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AddressRepositoryImpl @Inject constructor(
    private val datasource: IRemoteDatasource
): IAddressRepository {

    private val addresses = mutableListOf<Address>()


    init {
        addresses.addAll(dummyAddresses(2))
    }

    override suspend fun getAllSavedAddressesAsFlow(): Flow<Result<List<Address>>> {
        return datasource.fetchAllAddressesForUser("").map { result ->
            when(result){
                is Result.Success -> {
                    val addresses = result.data.map {
                        Address(it)
                    }
                    Result.Success(addresses)
                }
                is Result.Error -> {
                    Result.Error(result.throwable)
                }
            }
        }
    }

    override suspend fun getAllSavedAddresses(): List<Address> {
        return addresses
    }

    override suspend fun getAddressById(id: ID): Address? {
        return addresses.find { it.id == id }
    }

    override suspend fun getAddressByIdAsFlow(id: ID): Flow<Result<Address>> {
        return datasource.fetchAddressById(id.value).map {
                result ->
            when(result){
                is Result.Success -> Result.Success(data = Address(result.data))
                is Result.Error -> Result.Error(result.throwable)
            }
        }
    }

    override suspend fun upsertAddress(address: Address) {
        addresses.add(address)
    }

    override suspend fun upsertAddressWithResultFlow(address: Address): Flow<Result<Address>> {
        return address.id?.let {
            datasource.updateAddress(
                userId = "",
                address = address.toAddressDto()
            ).map { result ->
                when(result){
                    is Result.Success -> Result.Success(data = Address(result.data))
                    is Result.Error -> Result.Error(result.throwable)
                }
            }
        } ?: datasource.insertAddress(
            userId = "",
            address = address.toAddressDto()
        ).map { result ->
            when(result){
                is Result.Success -> Result.Success(data = Address(result.data))
                is Result.Error -> Result.Error(result.throwable)
            }
        }
    }
}