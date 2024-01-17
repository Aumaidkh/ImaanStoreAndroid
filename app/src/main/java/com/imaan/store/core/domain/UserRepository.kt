package com.imaan.store.core.domain

import com.imaan.store.feature_auth.domain.model.UserModel

interface UserRepository {

    suspend fun fetchUserData(): UserModel
}