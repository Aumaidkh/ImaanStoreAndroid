package com.imaan.products

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.imaan.cart.ICartRepository
import com.imaan.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val QUERY_KEY = "query"

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    productsRepository: IProductRepository,
    private val cartRepository: ICartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProductsScreenUiState())
    val state = _state.asStateFlow()

    private val sortOrder = MutableStateFlow(SortOrder.PRICE_LOW_TO_HIGH)

    private var query: String? = null

    init {
        savedStateHandle.get<String>(QUERY_KEY).toString().also { query ->
            this.query = query.split("-")[0]
            val name = query.split("-")[1]
            _state.update {
                it.copy(
                    query = name
                )
            }
        }
        viewModelScope.launch {
//            productsRepository
//                .fetchProductsWithPagination()
//                .distinctUntilChanged()
//                .cachedIn(viewModelScope)
//                .collect { pagingDate ->
//                    _state.update {
//                        it.copy(
//                            pagingData = pagingDate.map { product -> product as ProductModel }
//                        )
//                    }
//                }
            productsRepository.fetchAllProductsAsFlow(category = query).collect { emission ->
                when (emission) {
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                products = emission.data.map { product ->
                                    product as ProductModel
                                }
                            )
                        }
                    }

                    is Result.Error -> {
                        Log.d(
                            " TAG",
                            "Error: ${emission.throwable.message}"
                        )
                    }
                }
            }
        }
    }

    fun addProductToCart(productModel: ProductModel){
        viewModelScope.launch {
            cartRepository.addProductToCart(productModel)
            _state.update {
                it.copy(
                    infoMessage = "Product added to cart"
                )
            }
        }
    }

    fun onErrorMessageHandled(){
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun onInfoMessageHandled(){
        _state.update {
            it.copy(
                infoMessage = null
            )
        }
    }
}