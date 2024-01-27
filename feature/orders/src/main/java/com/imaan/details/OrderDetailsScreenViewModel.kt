package com.imaan.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.common.model.ID
import com.imaan.common.wrappers.Result
import com.imaan.navigation.OrderIdKey
import com.imaan.navigation.Orders
import com.imaan.order.IOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class OrderDetailsScreenViewModel @Inject constructor(
    private val orderRepository: IOrderRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(OrderDetailsUiState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(OrderIdKey)?.let {
            populateOrderIntoState(id = ID(it))
        }
    }
    @VisibleForTesting
    fun populateOrderIntoState(id: ID?) {
        if (id == null){
            return
        }
        viewModelScope.launch {
            orderRepository.getOrderById(id).also { result ->
                when(result){
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.throwable.message
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                order = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}