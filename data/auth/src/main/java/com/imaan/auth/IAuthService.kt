package com.imaan.auth

import com.imaan.util.Result
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow

interface IAuthService {

    val currentUser: User?

    val authResult: Flow<Result<User>>
    suspend fun authenticate(): User

}