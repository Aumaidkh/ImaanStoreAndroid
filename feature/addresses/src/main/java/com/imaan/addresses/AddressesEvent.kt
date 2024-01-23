package com.imaan.addresses

sealed interface AddressesEvent {
    data class Success<T>(val data: T): AddressesEvent

    data class Error(val throwable: Throwable): AddressesEvent
}