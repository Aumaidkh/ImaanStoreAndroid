package com.imaan.store.core.presentation.utils

sealed interface UiEvent {

    data class Error(val throwable: Throwable): UiEvent {
        val errorMessage get() = throwable.message.toString()
    }

    data class Success<out T>(val data: T? = null): UiEvent
}