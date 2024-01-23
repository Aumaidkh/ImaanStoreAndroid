package com.imaan.common.wrappers

sealed class Result<T>{
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val throwable: Throwable): Result<T>()
}