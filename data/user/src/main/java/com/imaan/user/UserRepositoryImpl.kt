package com.imaan.user

import com.imaan.common.model.Email
import com.imaan.common.model.Username
import java.net.URL
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(): IUserRepository {

    override suspend fun getCurrentUser(): UserModel {
        return UserModel(
            username = Username("Murtaza"),
            email = Email("aumaid@gmail.com"),
            profilePicUrl = URL("https://images.pexels.com/photos/91227/pexels-photo-91227.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        )
    }
}