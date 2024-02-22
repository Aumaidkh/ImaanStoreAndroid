package com.imaan.remote

import android.util.Log
import com.imaan.auth.IAuthService
import com.imaan.remote.dto.Inventory
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
import org.mongodb.kbson.BsonObjectId
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
            copyToRealm(Inventory().apply {
                productId = org.mongodb.kbson.ObjectId("65d32c41e9b3cd7cf011b8e9")
                stocks = 100
                price = 10.0
                discount = 3.0
            })
        }
    }

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> {
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

    override suspend fun fetchInventories(): Flow<Result<List<Inventory>>> {
        return realm.query<Inventory>()
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchInventories: No items"
                    )
                    Result.Error(Throwable("No Inventory items Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchInventories: ${result.list.size}"
                    )
                    Result.Success(result.list.toList())
                }
            }
    }

    private fun configure(){
        authService.currentUser?.let {
            val config = SyncConfiguration.Builder(
                it,
                setOf(Product::class,Inventory::class)
            )
                .initialSubscriptions { sub ->
                    add(query = sub.query<Product>())
                    add(query = sub.query<Inventory>())
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }
}