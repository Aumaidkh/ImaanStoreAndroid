package com.imaan.store.feature_auth.domain.repository

import com.imaan.store.feature_auth.domain.model.AuthenticationStatus
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.model.OtpResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeAuthRepository @Inject constructor(): IAuthRepository {

    var error: Throwable? = null
    var otp: Int = 1234

    override suspend fun requestOtpOn(phone: String): OtpResult {
        if (error != null){
            return OtpResult(
                otp = null,
                error = error
            )
        }

        return OtpResult(
            otp = OTP(otp),
            error = null
        )
    }

    override suspend fun verifyOtp(otp: String): AuthenticationStatus<Nothing> {
        error?.let {
            return AuthenticationStatus.Error(it)
        }

        return AuthenticationStatus.Success()
    }

    override suspend fun register(phone: String, fullName: String, email: String?): AuthenticationStatus<OTP> {
        error?.let {
            return AuthenticationStatus.Error(it)
        }

        return AuthenticationStatus.Success()
    }
}