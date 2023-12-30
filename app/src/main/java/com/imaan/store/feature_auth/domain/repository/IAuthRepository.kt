package com.imaan.store.feature_auth.domain.repository

interface IAuthRepository {
    fun requestOtpOn(phone: String)
}