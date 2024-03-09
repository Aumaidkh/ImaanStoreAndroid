package com.imaan.categories

import com.imaan.common.model.ID
import com.imaan.common.model.Title
import com.imaan.remote.IRemoteDatasource
import com.imaan.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val datasource: IRemoteDatasource
) : ICategoryRepository {
    override suspend fun fetchCategories(): List<CategoryModel> {
        return getDummyCategories(9)
    }

    override fun fetchCategoriesFlow(): Flow<Result<List<CategoryModel>>> {
        return datasource.fetchCategories().map { result ->
            when(result){
                is Result.Success -> {
                    val categories = result.data.map {
                        CategoryModel(
                            id = ID(it._id.toHexString()),
                            label = Title(it.label),
                            backgroundImageUrl = URL(it.image),
                            iconUrl = URL(it.image),
                        )
                    }
                    Result.Success(categories)
                }
                is Result.Error -> Result.Error(result.throwable)
            }
        }
    }
}

