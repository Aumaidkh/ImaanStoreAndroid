package com.imaan.store.feature_auth.domain.repository

import com.imaan.store.feature_auth.domain.model.AuthenticationStatus
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.model.OtpResult

interface IAuthRepository {
    suspend fun requestOtpOn(phone: String): OtpResult

    suspend fun verifyOtp(otp: String): AuthenticationStatus<Nothing>

    suspend fun register(phone: String,fullName: String,email: String? = null): AuthenticationStatus<OTP>
}