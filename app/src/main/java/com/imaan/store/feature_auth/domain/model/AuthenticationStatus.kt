package com.imaan.store.feature_auth.domain.model

sealed interface AuthenticationStatus<T>{
    data class Success<T>(val data: T? = null): AuthenticationStatus<T>

    data class Error<T>(val throwable: Throwable): AuthenticationStatus<T>
}