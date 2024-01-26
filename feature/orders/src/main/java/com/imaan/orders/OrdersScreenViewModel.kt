package com.imaan.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.wrappers.Result
import com.imaan.order.IOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersScreenViewModel @Inject constructor(
    private val orderRepository: IOrderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OrdersUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fetchOrderHistory()
        }
    }
    private suspend fun fetchOrderHistory() {
        viewModelScope.launch {
            orderRepository.fetchAllOrdersOfUser("").also { result ->
                when(result){
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = result.throwable.message
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                orders = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun onErrorHandled(){
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }
}