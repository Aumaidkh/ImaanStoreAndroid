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
import kotlinx.coroutines.flow.combine
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
        val productsFlow = datasource.fetchProducts()
        val inventoriesFlow = datasource.fetchInventories()

        return combine(productsFlow,inventoriesFlow){ productsResult, inventoriesResult ->
            if (productsResult is Result.Error || inventoriesResult is Result.Error){
                Result.Error(Exception("Something went wrong"))
            } else {
                val inventories = (inventoriesResult as Result.Success).data.associateBy { it.productId }
                val products = (productsResult as Result.Success).data

                val productModels = products.map {
                    ProductModel(
                        id = ID(it._id.toHexString()),
                        imageUrl = URL(it.thumbnailUrl),
                        title = Title(it.name),
                        description = Description(it.description),
                        price = Amount(inventories[it._id]?.price ?: 0.0),
                        stocks = Stocks(inventories[it._id]?.stocks ?: 0),
                        discount = Discount(inventories[it._id]?.discount?.toFloat() ?: 0.0f),
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

                Result.Success(productModels)
            }

        }

    }

    override suspend fun fetchProductWithId(id: ID): Result<ProductModel> {
        return Result.Success(
            data = dummyProduct
        )
    }
}