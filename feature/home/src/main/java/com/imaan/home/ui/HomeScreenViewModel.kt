package com.imaan.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.cart.ICartRepository
import com.imaan.categories.CategoryModel
import com.imaan.categories.ICategoryRepository
import com.imaan.offers.IOffersRepository
import com.imaan.products.IProductRepository
import com.imaan.products.ProductModel
import com.imaan.products.model.IProductModel
import com.imaan.user.IUserRepository
import com.imaan.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val cart: ICartRepository,
    private val categories: ICategoryRepository,
    private val offers: IOffersRepository,
    private val products: IProductRepository,
    private val user: IUserRepository,
): ViewModel(){
    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<HomeEvent<String>>()
    val event get() = _event.receiveAsFlow()

    init {
        loadData()
        viewModelScope.launch {
            loadProducts()
        }
    }

    fun onSelectCategory(categoryModel: CategoryModel){
        add()
        _state.update {
            it.copy(
                selectedCategory = categoryModel
            )
        }
    }

    fun onAddToCart(productModel: IProductModel){
        viewModelScope.launch {
            cart.addProductToCart(productModel).also { added ->
                if (added){
                    _state.update {
                        it.copy(
                            cartItemCount = _state.value.cartItemCount + 1
                        )
                    }
                    _event.send(
                        element = HomeEvent.Success("Item added to cart")
                    )
                }
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }

            listOf(
                async { loadUser() },
                async { loadCart() },
                async { loadOffers() },
                async { loadCategories() },
            ).awaitAll()

            _state.update {
                it.copy(
                    loading = false
                )
            }
        }
    }

    private suspend fun loadCart(){
        _state.update {
            it.copy(
                cartItemCount = cart.fetchCartItems().size
            )
        }
    }
    private suspend fun loadUser(){
        _state.update {
            it.copy(
                user = user.getCurrentUser()
            )
        }
    }

    fun add(){
        viewModelScope.launch {
            products.insertProduct()
        }
    }

    private suspend fun loadOffers(){
        _state.update {
            it.copy(
                offers = offers.fetchAllOffers()
            )
        }
    }

    private suspend fun loadCategories(){
        val categories = categories.fetchCategories()
        _state.update {
            it.copy(
                categories = categories,
                selectedCategory = categories.firstOrNull()
            )
        }
    }
    private val TAG = "HomeScreenViewModel"
    private suspend fun loadProducts(){
        Log.d(
            TAG,
            "loadProducts: Loading products"
        )
        products.fetchAllProductsAsFlow().collect { productResult ->
            when(productResult){
                is Result.Error -> {
                    Log.d(
                        TAG,
                        "loadProducts: Error: ${productResult.throwable.message}"
                    )
                }
                is Result.Success -> {
                    Log.d(
                        TAG,
                        "loadProducts: Success"
                    )
                    val products = productResult.data
                    _state.update {
                        it.copy(
                            products = products
                        )
                    }
                }
            }
        }

    }
}