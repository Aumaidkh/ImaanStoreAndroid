package com.imaan.remote

import android.util.Log
import com.imaan.auth.IAuthService
import com.imaan.remote.dto.Product
import com.imaan.util.Result
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "MongoDatasource"

@Singleton
class MongoDatasource @Inject constructor(
    private val app: App,
    private val authService: IAuthService
): IRemoteDatasource {

    private val user = authService.currentUser
    private lateinit var realm: Realm

    init {
        configure()
    }
    override suspend fun fetchProducts(): Flow<Result<List<Product>>> {
        return flow {
            user?.let {
                realm.query<Product>()
                    .asFlow()
                    .map { result ->
                        Log.d(
                            TAG,
                            "getProducts: Result: ${result.list.toList()}"
                        )
                        result.list.toList().also {
                            if (it.isEmpty()){
                                Result.error<List<Product>>("No Products Found!!")
                            } else {
                                Result.Success(it)
                            }
                        }
                    }
            }?: Result.error<List<Product>>("User not authenticated")
        }
    }

    private fun configure(){
        user?.let {
            val configuration =
                RealmConfiguration.create(setOf(Product::class))
                realm = Realm.open(configuration)
        }
    }
}