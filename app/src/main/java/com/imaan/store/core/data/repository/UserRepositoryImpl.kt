package com.imaan.store.core.data.repository

import com.imaan.store.core.domain.UserRepository
import com.imaan.store.feature_auth.domain.model.UserModel
import kotlinx.coroutines.delay

class UserRepositoryImpl: UserRepository {

    override suspend fun fetchUserData(): UserModel {
      //  delay(1000)
        return UserModel(
            username = "Murtaza",
            email = "",
        )
    }
}