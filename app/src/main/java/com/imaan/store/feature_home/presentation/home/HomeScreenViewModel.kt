package com.imaan.store.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.data.repository.CartRepositoryImpl
import com.imaan.store.core.data.repository.CategoryRepositoryImpl
import com.imaan.store.core.data.repository.OfferRepositoryImpl
import com.imaan.store.core.data.repository.ProductRepositoryImpl
import com.imaan.store.core.data.repository.UserRepositoryImpl
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_home.domain.models.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel(){
    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event get() = _event.receiveAsFlow()

    private val userRepo = UserRepositoryImpl()
    private val categoryRepo = CategoryRepositoryImpl()
    private val offerRepo = OfferRepositoryImpl()
    private val productRepo = ProductRepositoryImpl()
    private val cartRepo = CartRepositoryImpl()

    init {
        loadData()
    }

    fun onSelectCategory(categoryModel: CategoryModel){
        _state.update {
            it.copy(
                selectedCategory = categoryModel
            )
        }
    }

    fun onAddToCart(productModel: ProductModel){
        viewModelScope.launch {
            cartRepo.addToCart(productModel).also { added ->
                if (added){
                    _state.update {
                        it.copy(
                            cartItemCount = _state.value.cartItemCount + 1
                        )
                    }
                    _event.send(
                        element = UiEvent.Success("Item added to cart")
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
                async { loadProducts() }
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
                cartItemCount = cartRepo.fetchCartItems().size
            )
        }
    }
    private suspend fun loadUser(){
        _state.update {
            it.copy(
                user = userRepo.fetchUserData()
            )
        }
    }

    private suspend fun loadOffers(){
        _state.update {
            it.copy(
                offers = offerRepo.fetchAllOffers()
            )
        }
    }

    private suspend fun loadCategories(){
        val categories = categoryRepo.fetchCategories()
        _state.update {
            it.copy(
                categories = categories,
                selectedCategory = categories.firstOrNull()
            )
        }
    }

    private suspend fun loadProducts(){
        _state.update {
            it.copy(
                products = productRepo.fetchAllProducts()
            )
        }
    }
}