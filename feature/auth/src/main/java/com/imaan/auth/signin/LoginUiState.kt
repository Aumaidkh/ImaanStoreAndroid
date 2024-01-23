package com.imaan.auth.signin

import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber

data class LoginScreenUiState(
    val loading: Boolean = false,
    val phone: PhoneNumber = PhoneNumber(),
    val message: String? = null,
    val otp: OTP? = null
)

