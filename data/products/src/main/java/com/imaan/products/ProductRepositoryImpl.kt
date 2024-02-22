package com.imaan.products

import android.util.Log
import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Stocks
import com.imaan.common.model.Title
import com.imaan.remote.IRemoteDatasource
import kotlinx.coroutines.flow.Flow
import com.imaan.util.Result
import kotlinx.coroutines.flow.map
import java.net.URL
import java.util.UUID
import javax.inject.Inject

private const val TAG = "ProductRepositoryImpl"
class ProductRepositoryImpl @Inject constructor(
    private val datasource: IRemoteDatasource,
) : IProductRepository {
    override suspend fun fetchAllProducts(offset: Int?): List<ProductModel> {
        return getDummyProducts(20)
    }

    override suspend fun insertProduct() {
        datasource.insertProduct()
    }

    override suspend fun fetchAllProductsAsFlow(offset: Int?): Flow<Result<List<ProductModel>>> {
        return datasource.fetchProducts().map { result ->
            if (result is Result.Success){
                val products = result.data.map {
                    ProductModel(
                        id = ID(it._id.toHexString()),
                        imageUrl = URL(it.thumbnailUrl),
                        title = Title(it.name),
                        description = Description(it.description),
                        price = Amount(100.0),
                        stocks = Stocks(120),
                        discount = Discount(0.3f),
                        images = listOf(
                            Image(
                                thumbnail = dummyUrl,
                                original = dummyUrl
                            ),
                            Image(
                                thumbnail = dummyUrl,
                                original = dummyUrl
                            ),
                            Image(
                                thumbnail = dummyUrl,
                                original = dummyUrl
                            ),
                            Image(
                                thumbnail = dummyUrl,
                                original = dummyUrl
                            ),
                            Image(
                                thumbnail = dummyUrl,
                                original = dummyUrl
                            ),
                        ),
                        colors = getDummyColors(),
                        sizes = getDummySizes(),
                        customVariants = getDummyVariants()
                    )
                }
                Log.d(
                    TAG,
                    "fetchAllProductsAsFlow: Products: ${products.size}"
                )
                Result.Success(products)
            } else {
                Log.d(
                    TAG,
                    "fetchAllProductsAsFlow: No Products"
                )
                Result.error("No Products found")
            }
        }
    }

    override suspend fun fetchProductWithId(id: ID): Result<ProductModel> {
        return Result.Success(
            data = dummyProduct
        )
    }
}