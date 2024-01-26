package com.imaan.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.cart.ICartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val sortOrder = MutableStateFlow<SortOrder>(SortOrder.PRICE_LOW_TO_HIGH)

    private var query: String? = null

    init {
        query = savedStateHandle.get<String>(QUERY_KEY).toString().also { query ->
            _state.update {
                it.copy(
                    query = query
                )
            }
        }
        viewModelScope.launch {
            productsRepository.fetchAllProducts(1).also { products ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        products = products
                    )
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