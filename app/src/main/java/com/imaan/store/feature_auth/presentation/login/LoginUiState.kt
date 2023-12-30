package com.imaan.store.feature_auth.presentation.login

sealed interface LoginUiState{
    val loading: Boolean
    val error: Throwable?

    data class Initial(
        override val loading: Boolean = false,
        override val error: Throwable? = null
    ): LoginUiState

    data class Loading(
        override val loading: Boolean = true,
        override val error: Throwable? = null
    ): LoginUiState


    data class Error(
        override val error: Throwable?,
        override val loading: Boolean = false
    ): LoginUiState


    data class OtpSent(
        override val loading: Boolean = false,
        override val error: Throwable? = null,
        val message: String
    ): LoginUiState
}