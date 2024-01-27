package com.imaan.tracking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result
import com.imaan.navigation.OrderIdKey
import com.imaan.order.IOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderTrackingScreenViewModel @Inject constructor(
    private val orderRepository: IOrderRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state = MutableStateFlow(OrderTrackingUiState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(OrderIdKey).also {
            it?.let {
                loadOrderWithId(ID(it))
            }
        }
    }

    private fun loadOrderWithId(id: ID){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            orderRepository.getOrderById(id).also { result ->
                when(result){
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = result.throwable.message,
                                isLoading = false
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                order = result.data,
                                isLoading = false
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