package com.imaan.store.feature_auth.data.repository

import com.imaan.store.feature_auth.data.network.api.AuthApiService
import com.imaan.store.feature_auth.data.network.utils.ApiResponse
import com.imaan.store.feature_auth.domain.model.AuthenticationStatus
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.model.OtpResult
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): IAuthRepository {

    private val apiService by lazy {
        AuthApiService()
    }
    override suspend fun requestOtpOn(phone: String): OtpResult {
        apiService.requestOtp(phone).also { response ->
            return when(response){
                is ApiResponse.Error -> OtpResult(
                    null,
                    response.error
                )

                is ApiResponse.Success -> {
                    OtpResult(
                        OTP.fromString(response.data?.otp!!),
                        null
                    )
                }
            }
        }

    }

    override suspend fun verifyOtp(otp: String): AuthenticationStatus<Nothing> {
        delay(2000)
        return AuthenticationStatus.Success()
    }

    override suspend fun register(phone: String, fullName: String, email: String?): AuthenticationStatus<OTP> {
        delay(2000)
        return AuthenticationStatus.Success(
            data = OTP.fromString("1234")
        )
    }
}