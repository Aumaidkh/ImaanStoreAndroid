package com.imaan.home.ui

sealed interface HomeEvent<out T> {
    data class Error(val throwable: Throwable): HomeEvent<Nothing>
    data class Success<out T>(val data: T): HomeEvent<T>
}