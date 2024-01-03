package com.imaan.store.feature_auth.presentation.verify_otp

import com.imaan.store.feature_auth.domain.model.UserModel

data class VerifyOtpUiState(
    val loading: Boolean = false,
    val user: UserModel = UserModel(),
    val otp: String = "",
    val showResendOtp: Boolean = false,
    val otpStatusMessage: String = ""
)
