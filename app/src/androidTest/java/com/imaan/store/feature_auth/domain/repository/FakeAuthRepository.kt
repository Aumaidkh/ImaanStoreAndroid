package com.imaan.store.feature_auth.domain.repository

import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.model.OtpModel
import javax.inject.Inject

class FakeAuthRepository @Inject constructor(): IAuthRepository {

    var error: Throwable? = null
    var otp: Int = 1234

    override suspend fun requestOtpOn(phone: String): OtpModel {
        if (error != null){
            return OtpModel(
                otp = null,
                error = error
            )
        }

        return OtpModel(
            otp = OTP(otp),
            error = null
        )
    }
}