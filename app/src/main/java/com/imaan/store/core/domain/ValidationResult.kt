package com.imaan.store.core.domain

data class ValidationResult(
    val isValid: Boolean,
    val error: Throwable? = null
){
    val errorMessage get() =
        error?.message.toString()
}