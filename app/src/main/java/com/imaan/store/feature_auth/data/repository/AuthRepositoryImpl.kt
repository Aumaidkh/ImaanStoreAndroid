package com.imaan.store.feature_auth.data.repository

import com.imaan.store.feature_auth.domain.model.OtpModel
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): IAuthRepository {
    override suspend fun requestOtpOn(phone: String): OtpModel {
        return OtpModel(
            null,
            Exception("Dummy Exception")
        )
    }
}