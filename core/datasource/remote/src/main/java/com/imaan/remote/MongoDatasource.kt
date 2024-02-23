package com.imaan.remote

import android.util.Log
import com.imaan.auth.IAuthService
import com.imaan.remote.dto.Inventory
import com.imaan.remote.dto.Product
import com.imaan.remote.dto.ProductVariant
import com.imaan.util.Result
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId
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
            copyToRealm(ProductVariant().apply {
                this.productId = org.mongodb.kbson.ObjectId("65d32b19e9b3cd7cf011b8e8")
                this.name = "IPhone 12 "
                this.hexColor = "#000000"
                this.size = null
                this.label = "6GB 128GB"
                this.label = "Black"
                this.images = realmListOf("https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/iphone-x-pictures-45216.png?alt=media&token=914f198d-6e91-4530-9ba8-bea56458c66d","https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/iphone-x-pictures-45216.png?alt=media&token=914f198d-6e91-4530-9ba8-bea56458c66d")
                this.specs = "Grab Yours at Discount"
            })
        }
    }

    override suspend fun fetchVariantsForProduct(id: String): Flow<Result<List<ProductVariant>>> {
        return realm.query<ProductVariant>("productId == $0",ObjectId(id))
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchVariants for product $id: Empty"
                    )
                    Result.Error(Throwable("No Products Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchVariants for product $id: ${result.list.size}"
                    )
                    Result.Success(result.list.toList())
                }
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

    override suspend fun fetchProductWithId(id: String): Flow<Result<List<Product>>> {
        return realm.query<Product>("_id == $0",ObjectId(id))
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchProductWithId: No Product found:"
                    )
                    Result.Error(Throwable("No Products Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchProductWithId: ${result.list.size}"
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
                    result.list.forEach {
                        Log.d(
                            TAG,
                            "fetchInventories: ${it.price} ${it.productId}"
                        )
                    }
                    Result.Success(result.list.toList())
                }
            }
    }

    override suspend fun fetchInventoriesForProduct(id: String): Flow<Result<List<Inventory>>> {
        return realm.query<Inventory>("productId == $0",ObjectId(id))
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchInventoriesForProduct: No items"
                    )
                    Result.Error(Throwable("No Inventory items Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchInventoriesForProduct: ${result.list.size}"
                    )
                    Result.Success(result.list.toList())
                }
            }
    }

    private fun configure(){
        authService.currentUser?.let {
            val config = SyncConfiguration.Builder(
                it,
                setOf(Product::class,Inventory::class,ProductVariant::class)
            )
                .initialSubscriptions { sub ->
                    add(query = sub.query<Product>())
                    add(query = sub.query<Inventory>())
                    add(query = sub.query<ProductVariant>())
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }
}