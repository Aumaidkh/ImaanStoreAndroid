package com.imaan.auth.register

import com.imaan.common.model.Email
import com.imaan.common.model.FullName
import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber

data class RegisterScreenUiState(
    val loading: Boolean = false,
    val buttonEnabled: Boolean = false,
    val fullName: FullName = FullName.Empty,
    val fullNameError: String? = null,
    val email: Email? = null,
    val emailError: String? = null,
    val phoneNumber: PhoneNumber = PhoneNumber.Empty,
    val phoneNumberError: String? = null,
    val otp: OTP? = null,
    val error: String? = null
)
