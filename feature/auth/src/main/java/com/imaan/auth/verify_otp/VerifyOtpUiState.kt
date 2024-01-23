package com.imaan.auth.verify_otp


import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber
import com.imaan.user.UserModel

data class VerifyOtpUiState(
    val loading: Boolean = false,
    val phoneNumber: PhoneNumber? = null,
    val user: UserModel? = null,
    val otp: OTP = OTP(""),
    val showResendOtp: Boolean = false,
    val otpStatusMessage: String = "",
    val authenticated: Boolean = false,
    val errorMessage: String? = null
)
