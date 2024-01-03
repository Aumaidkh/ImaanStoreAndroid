package com.imaan.store.feature_auth.domain.model

data class OtpResult(
    val otp: OTP?,
    val error: Throwable?
)


