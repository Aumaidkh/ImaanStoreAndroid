package com.imaan.product_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.cart.CartItemModel
import com.imaan.cart.ICartRepository
import com.imaan.common.model.Amount
import com.imaan.common.model.ID
import com.imaan.delivery.repository.IDeliveryService
import com.imaan.navigation.productIdKey
import com.imaan.order.IOrderRepository
import com.imaan.order.OrderModel
import com.imaan.products.ColorVariant
import com.imaan.products.CustomVariant
import com.imaan.products.IProductRepository
import com.imaan.products.ProductModel
import com.imaan.products.SizeVariant
import com.imaan.products.model.DetailedProductModel
import com.imaan.products.model.IProductModel
import com.imaan.products.model.IProductVariant
import com.imaan.products.model.ProductColorVariant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import java.util.UUID
import javax.inject.Inject

private const val TAG = "ProductDetailsScreenVie"
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
            productsRepository.fetchDetailedProductWithId(id).onEach { emission ->
                when(emission){
                    is com.imaan.util.Result.Error -> {
                        Log.d(
                            TAG,
                            "loadProductWithId: Error fetching product ${emission.throwable.message}"
                        )
                    }
                    is com.imaan.util.Result.Success -> {
                        val detailedProduct = emission.data as? DetailedProductModel
                        _state.update {
                            it.copy(
                                product = detailedProduct,
                                selectedProductVariant = detailedProduct?.variants?.firstOrNull()
                            )
                        }
                    }
                }
            }.launchIn(this)
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

    fun selectSize(size: IProductVariant){
        _state.update {
            it.copy(
               selectedSizeVariant = size,
            )
        }
    }

    fun selectColor(color: IProductVariant){
        _state.update {
            it.copy(
                selectedColorVariant = color
            )
        }
    }

    fun selectCustom(label: IProductVariant){
        _state.update {
            it.copy(
                selectedCustomVariant = label
            )
        }
    }

    // 65d9825ec804ebbf5f016aad
    // 65d9825ec804ebbf5f016aad -> Size
    // 65d986cdc804ebbf5f016aaf -> Color
    //
    fun selectVariant(){
        _state.update { currentState ->
            val selectedVariant = currentState.product?.variants?.find { variant ->
                Log.d(
                    TAG,
                    "selectVariant: Size: ${currentState.selectedSizeVariant?.id?.value} -> ${variant.id.value}" +
                            "\n Color: ${currentState.selectedColorVariant?.id?.value} -> ${variant.id.value}" +
                            "\n Custom: ${currentState.selectedCustomVariant?.id?.value} -> ${variant.id.value}"
                )
                variant == currentState.selectedSizeVariant &&
                        (variant as? ProductColorVariant) == currentState.selectedColorVariant &&
                        variant.label == currentState.selectedCustomVariant?.label
            }
            currentState.copy(
                selectedProductVariant = selectedVariant
            )
        }
    }

    fun buyNow(product: IProductModel){
        viewModelScope.launch {
            val cartItem = CartItemModel(ID(UUID.randomUUID().toString()),product,1)
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