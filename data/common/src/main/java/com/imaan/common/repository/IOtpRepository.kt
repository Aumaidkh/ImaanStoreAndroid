package com.imaan.common.repository

import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber
import com.imaan.common.wrappers.Result

interface IOtpRepository {

    suspend fun requestOtpOn(phone: PhoneNumber): Result<OTP>

    suspend fun verifyOtp(otp: OTP,phone: PhoneNumber): Result<VerificationStatus>

    enum class VerificationStatus{
        SUCCESS,
        ERROR
    }
}