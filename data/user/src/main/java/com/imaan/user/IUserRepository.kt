package com.imaan.user

import com.imaan.common.model.OTP
import com.imaan.common.wrappers.Result

interface IUserRepository {
    suspend fun getCurrentUser(): UserModel

    suspend fun registerUser(user: UserModel): Result<OTP>


}