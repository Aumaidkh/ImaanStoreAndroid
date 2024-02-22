package com.imaan.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.cart.CartItemModel
import com.imaan.cart.ICartRepository
import com.imaan.common.model.Amount
import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result
import com.imaan.delivery.repository.IDeliveryService
import com.imaan.navigation.productIdKey
import com.imaan.order.IOrderRepository
import com.imaan.order.OrderModel
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.IProductRepository
import com.imaan.products.ProductModel
import com.imaan.products.SizeVariant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class ProductDetailsScreenViewModel @Inject constructor(
    private val productsRepository: IProductRepository,
    private val orderRepository: IOrderRepository,
    private val deliveryService: IDeliveryService,
    private val cartRepository: ICartRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state = MutableStateFlow(ProductDetailsScreenUiState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(productIdKey).also {
            it?.let {
                loadProductWithId(
                    id = ID(it)
                )
            }
        }
    }

    @VisibleForTesting
    fun loadProductWithId(id: ID){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val recommendedProductsResult = async {
                productsRepository.fetchAllProducts()
                    .also { products ->
                        _state.update {
                            it.copy(
                                recommendedProducts = products
                            )
                        }
                    }
            }
            val productResult = async {
//                productsRepository.fetchProductWithId(id).also { result ->
//                    when(result){
//                        is Result.Error -> {
//                            _state.update {
//                                it.copy(
//                                    isLoading = false,
//                                    errorMessage = result.throwable.message
//                                )
//                            }
//                        }
//                        is Result.Success -> {
//                            _state.update {
//                                it.copy(
//                                    isLoading = false,
//                                    product = result.data,
//                                    selectedSize = result.data.sizes.firstOrNull(),
//                                    selectedColor = result.data.colors.firstOrNull(),
//                                    selectedVariant = result.data.customVariants.firstOrNull()
//                                )
//                            }
//                        }
//                    }
//                }
            }

            recommendedProductsResult.await()
            productResult.await()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
//            productsRepository.fetchProductWithId(id).also { result ->
//                when(result){
//                    is Result.Error -> {
//                        _state.update {
//                            it.copy(
//                                isLoading = false,
//                                errorMessage = result.throwable.message
//                            )
//                        }
//                    }
//                    is Result.Success -> {
//                        _state.update {
//                            it.copy(
//                                isLoading = false,
//                                product = result.data,
//                            )
//                        }
//                    }
//                }
//            }
        }
    }

    fun onErrorHandled(){
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun selectSize(size: SizeVariant){
        _state.update {
            it.copy(
                selectedSize = size
            )
        }
    }

    fun selectColor(color: ColorVariant?){
        _state.update {
            it.copy(
                selectedColor = color
            )
        }
    }

    fun selectVariant(variant: CustomVariant?){
        _state.update {
            it.copy(
                selectedVariant = variant
            )
        }
    }

    fun buyNow(product: ProductModel){
        viewModelScope.launch {
            val cartItem = CartItemModel(product,1)
            orderRepository.updateOrder(
                orderModel = OrderModel(
                    cartItems = listOf(cartItem),
                    deliveryCharges = deliveryService.calculateAndReturnDeliveryCharges(),
                    discount = Amount.ZERO
                )
            )
            _state.update {
                it.copy(
                    buyProductNow = true
                )
            }
        }
    }

    fun onBuyNowHandled(){
        _state.update {
            it.copy(
                buyProductNow = false
            )
        }
    }

}