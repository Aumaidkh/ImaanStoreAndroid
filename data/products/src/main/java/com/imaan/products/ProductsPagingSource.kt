package com.imaan.products

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imaan.common.model.Amount
import com.imaan.common.model.Description
import com.imaan.common.model.Discount
import com.imaan.common.model.ID
import com.imaan.common.model.Image
import com.imaan.common.model.Stocks
import com.imaan.common.model.Title
import com.imaan.products.model.IProductModel
import com.imaan.remote.IRemoteDatasource
import com.imaan.util.Result
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException
import java.net.URL

class ProductsPagingSource(
    private val datasource: IRemoteDatasource
) : PagingSource<Int, IProductModel>() {

    override fun getRefreshKey(state: PagingState<Int, IProductModel>): Int? {
        return state.anchorPosition
    }

    @RequiresExtension(
        extension = Build.VERSION_CODES.S,
        version = 7
    )
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IProductModel> {
        try {
            val currentPage = params.key ?: 1
            val productResult = datasource.fetchProductsByCategory(
                "",
                ""
            ).firstOrNull() ?: return LoadResult.Error(Exception("No inventory found"))
            val inventoryResult = datasource.fetchInventories().firstOrNull() ?: return LoadResult.Error(Exception("No inventory found"))
            if (productResult is Result.Error || inventoryResult is Result.Error){
                return LoadResult.Error(Exception("Something went wrong"))
            }
            val productResults = (productResult as Result.Success).data
            val inventories = (inventoryResult as Result.Success).data.associateBy { it.productId }
            val products = productResults.map {
                ProductModel(
                    id = ID(it._id.toHexString()),
                    title = Title(it.name),
                    description = Description(it.description),
                    price = Amount(inventories[it._id]?.price ?: 0.0),
                    stocks = Stocks(inventories[it._id]?.stocks ?: 0),
                    discount = Discount(inventories[it._id]?.discount?.toFloat() ?: 0.0f),
                    category = "Electronics",
                    image = Image(
                        thumbnail = URL(it.thumbnailUrl),
                        original = URL(it.thumbnailUrl)
                    )
                )
            }
            return LoadResult.Page(
                data = products,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (products.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}