package com.imaan.auth

import android.util.Log
import com.imaan.util.Result
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.LoggedIn
import io.realm.kotlin.mongodb.LoggedOut
import io.realm.kotlin.mongodb.Removed
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "MongoAuthService"
internal class MongoAuthService @Inject constructor(
    private val app: App
): IAuthService {
    override val currentUser: User?
        get() = app.currentUser
    override val authResult: Flow<Result<User>>
        get() = app.authenticationChangeAsFlow().map { result ->
            when(result){
                is LoggedIn -> {
                    Log.d(
                        TAG,
                        "Success: Auth"
                    )
                    Result.Success(data = result.user)
                }
                is LoggedOut -> {
                    Log.d(
                        TAG,
                        "Logged Out: "
                    )
                    Result.Error(Exception("User Not Authenticated"))
                }
                is Removed -> {
                    Log.d(
                        TAG,
                        "Removed: "
                    )
                    Result.Error(Exception("User removed"))
                }
            }
        }

    override suspend fun authenticate(): User {
        return app.login(Credentials.anonymous(true))
    }
}