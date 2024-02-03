package com.imaan.delivery.repository

import com.imaan.addresses.Address
import com.imaan.addresses.dummyAddresses
import com.imaan.common.model.Amount

interface IDeliveryService {
    suspend fun calculateAndReturnDeliveryCharges(address: Address = dummyAddresses(1)[0]): Amount
}