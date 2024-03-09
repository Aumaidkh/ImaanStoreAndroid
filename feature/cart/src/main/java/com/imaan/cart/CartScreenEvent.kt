package com.imaan.cart

interface CartScreenEvent<out T> {
    data class Success<out T>(val data: T?):CartScreenEvent<T>
    data class Error(val throwable: Throwable): CartScreenEvent<Nothing>
}
