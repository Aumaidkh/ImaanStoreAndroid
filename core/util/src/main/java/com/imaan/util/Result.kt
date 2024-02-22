package com.imaan.util

sealed class Result<T>{
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val throwable: Throwable): Result<T>()

    companion object {
        fun <T> error(message: String): Error<T>{
            return Error(Exception(message))
        }
    }
}