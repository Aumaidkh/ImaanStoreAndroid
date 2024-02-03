package com.imaan.delivery.repository

import com.imaan.addresses.Address
import com.imaan.common.model.Amount
import javax.inject.Inject

internal class DeliveryServiceImpl @Inject constructor(): IDeliveryService {
    override suspend fun calculateAndReturnDeliveryCharges(address: Address): Amount {
        return Amount(40.00)
    }
}