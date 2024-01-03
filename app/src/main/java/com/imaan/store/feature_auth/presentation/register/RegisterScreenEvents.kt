package com.imaan.store.feature_auth.presentation.register

import java.net.URL

/**
 * 1. Registering Error
 * 2. OnUrlClick
 * 3. Navigate */
sealed interface RegisterScreenEvent {
    data class Error(val throwable: Throwable): RegisterScreenEvent {
        val errorMessage get() = throwable.message.toString()
    }

    data class Success<T>(val data: T? = null): RegisterScreenEvent
}