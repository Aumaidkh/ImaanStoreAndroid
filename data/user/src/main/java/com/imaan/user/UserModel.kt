package com.imaan.user

import android.graphics.Bitmap
import com.imaan.common.model.Email
import com.imaan.common.model.PhoneNumber
import com.imaan.common.model.Username
import java.net.URI
import java.net.URL

data class UserModel(
    val username: Username,
    val email: Email? = null,
    val profilePicUrl: URL? = null,
    val phone: PhoneNumber,
    val imageUri: URI? = null
)

val dummyUser = UserModel(
    username = Username("Murtaza"),
    email = null,
    profilePicUrl = URL("https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
    phone = PhoneNumber("7889534384")
)