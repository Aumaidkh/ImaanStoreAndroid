package com.imaan.remote

import android.util.Log
import com.imaan.auth.IAuthService
import com.imaan.remote.dto.Address
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId
import java.util.UUID
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
            copyToRealm(Address().apply {
                this.fullName = "Murtaza Khursheed"
                this.label = "Office"
                this.country = "India"
                this.state = "J&K"
                this.district = "Anantnag"
                this.zipCode = "192101"
                this.phoneNumber = "7889534384"
                this.fullAddress = "Bongam Kadapora"
                this.landmark = "Bridge"
                this.alternatePhone = null
            })
            //            copyToRealm(ProductVariant().apply {
            //                this.productId = org.mongodb.kbson.ObjectId("65d32b19e9b3cd7cf011b8e8")
            //                this.name = "IPhone 12 "
            //                this.hexColor = "#000000"
            //                this.size = null
            //                this.label = "6GB 128GB"
            //                this.label = "Black"
            //                this.images = realmListOf("https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/iphone-x-pictures-45216.png?alt=media&token=914f198d-6e91-4530-9ba8-bea56458c66d","https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/iphone-x-pictures-45216.png?alt=media&token=914f198d-6e91-4530-9ba8-bea56458c66d")
            //                this.specs = "Grab Yours at Discount"
            //            })
            //            copyToRealm(Inventory().apply {
            //                this.productId = org.mongodb.kbson.ObjectId("65d32b19e9b3cd7cf011b8e8")
            //                this.discount = 0.0
            //                this.price = 20.0
            //                this.stocks = 100
            //            })
            //            copyToRealm(Product().apply {
            //                this.name = "Hot Wheels Die Cast Car"
            //                this.description = "This is a Iron Die cast car by Hot Wheels"
            //                this.thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/wallpapers-ff4ce.appspot.com/o/iphone-x-pictures-45216.png?alt=media&token=914f198d-6e91-4530-9ba8-bea56458c66d"
            //            })
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

    override suspend fun fetchAddressById(addressId: String): Flow<Result<Address>> {
        return realm.query<Address>(
            "_id == $0",
            ObjectId(addressId)
        )
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchAllAddressesForUser: No items"
                    )
                    Result.Error(Throwable("No Addresses Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchAllAddressesForUser: ${result.list.size}"
                    )
                    Result.Success(result.list.toList().first())
                }
            }
    }

    override suspend fun fetchAllAddressesForUser(userId: String): Flow<Result<List<Address>>> {
        return realm.query<Address>()
            .asFlow()
            .map { result ->
                if (result.list.isEmpty()) {
                    Log.d(
                        TAG,
                        "fetchAllAddressesForUser: No items"
                    )
                    Result.Error(Throwable("No Addresses Found"))
                } else {
                    Log.d(
                        TAG,
                        "fetchAllAddressesForUser: ${result.list.size}"
                    )
                    Result.Success(result.list.toList())
                }
            }
    }

    override suspend fun updateAddress(
        userId: String,
        address: Address
    ): Flow<Result<Address>> {
        realm.write {
            val liveAddress = query<Address>(
                "_id == $0",
                address._id
            ).find().firstOrNull()
            liveAddress.apply {
                this?.fullName = address.fullName
                this?.label = address.label
                this?.country = address.country
                this?.state = address.state
                this?.district = address.district
                this?.zipCode = address.zipCode
                this?.phoneNumber = address.phoneNumber
                this?.fullAddress = address.fullAddress
                this?.landmark = address.landmark
                this?.alternatePhone = null
            }
        }?.also {
            return flowOf(Result.Success(it))
        }
        return flowOf(Result.Error(Throwable("Error updating address")))
    }

    override suspend fun insertAddress(
        userId: String,
        address: Address
    ): Flow<Result<Address>> {
        return flow<Result<Address>> {
            realm.write {
                copyToRealm(address)
            }
            emit(Result.Success(address))
        }.catch {
            emit(Result.Error(it))
        }
    }


    private fun configure() {
        authService.currentUser?.let {
            val config = SyncConfiguration.Builder(
                it,
                setOf(
                    Product::class,
                    Inventory::class,
                    ProductVariant::class,
                    Address::class
                )
            )
                .initialSubscriptions { sub ->
                    add(query = sub.query<Product>())
                    add(query = sub.query<Inventory>())
                    add(query = sub.query<ProductVariant>())
                    add(query = sub.query<Address>())
                }
                .log(LogLevel.ALL)
                .build()
            //            val config = RealmConfiguration.create(
            //                setOf(Product::class,Inventory::class,ProductVariant::class,Address::class)
            //            )
            realm = Realm.open(config)
        }
    }
}