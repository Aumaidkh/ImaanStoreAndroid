package com.imaan.profile

import com.imaan.user.UserModel
import com.imaan.user.dummyUser

data class ProfileScreenUiState(
    val user: UserModel = dummyUser,
    val message: String? = null
)
