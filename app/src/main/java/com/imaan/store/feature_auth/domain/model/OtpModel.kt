package com.imaan.store.feature_auth.domain.model

data class OtpModel(
    val otp: OTP?,
    val error: Throwable?
)

data class OTP(
    val value: Int
)
