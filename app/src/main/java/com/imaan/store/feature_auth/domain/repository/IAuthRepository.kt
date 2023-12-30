package com.imaan.store.feature_auth.domain.repository

import com.imaan.store.feature_auth.domain.model.OtpModel

interface IAuthRepository {
    suspend fun requestOtpOn(phone: String): OtpModel
}