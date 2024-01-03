package com.imaan.store.feature_auth.presentation.login

import com.imaan.store.feature_auth.presentation.register.PhoneNumber

data class LoginScreenUiState(
    val loading: Boolean = false,
    val phone: PhoneNumber = PhoneNumber()
)

