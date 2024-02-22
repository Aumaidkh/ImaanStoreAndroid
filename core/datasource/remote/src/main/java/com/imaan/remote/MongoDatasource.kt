package com.imaan.remote

import android.util.Log
import com.imaan.auth.IAuthService
import com.imaan.remote.dto.Product
import com.imaan.util.Result
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "MongoDatasource"

@Singleton
class MongoDatasource @Inject constructor(
    private val app: App,
    private val authService: IAuthService
): IRemoteDatasource {

    private lateinit var realm: Realm

    init {
        configure()
    }

    override suspend fun insertProduct() {
        realm.write {
            copyToRealm(Product())
        }
    }

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> {
        Log.d(
            TAG,
            "fetchProducts:  "
        )
        return realm.query<Product>()
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchProducts: No Products"
                    )
                    Result.Error(Throwable("No Products Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchProducts: ${result.list.size}"
                    )
                    Result.Success(result.list.toList())
                }
            }
        }


    private fun configure(){
        authService.currentUser?.let {
            val config = SyncConfiguration.Builder(
                it,
                setOf(Product::class)
            )
                .initialSubscriptions { sub ->
                    add(query = sub.query<Product>())
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }
}