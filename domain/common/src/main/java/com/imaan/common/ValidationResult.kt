package com.imaan.common

data class ValidationResult(
    val isValid: Boolean,
    val error: Throwable? = null
)
