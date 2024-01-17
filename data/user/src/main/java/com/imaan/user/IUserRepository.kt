package com.imaan.user

interface IUserRepository {
    suspend fun getCurrentUser(): UserModel
}