package com.imaan.user

import com.imaan.common.model.Email
import com.imaan.common.model.Username
import java.net.URL

data class UserModel(
    val username: Username,
    val email: Email,
    val profilePicUrl: URL
)