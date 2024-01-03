package com.imaan.store.feature_auth.data.network.utils

sealed class ApiResponse<T>(
    open val data: T? = null,
    open val error: Throwable? = null
){
    data class Success<T>(override val data: T?): ApiResponse<T>(data,null)
    data class Error<T>(override val error: Throwable): ApiResponse<T>(null,error)
}
