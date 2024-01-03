package com.imaan.store.core.presentation.utils

sealed interface Event{
    data class Error(val throwable: Throwable): Event{
        val message get() = throwable.message.toString()
    }

    data class Navigate(val route: String): Event
}