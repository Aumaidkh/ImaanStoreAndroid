package com.imaan.store.feature_manage_addresses.presentation.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.OrderRepository
import com.imaan.store.core.domain.model.Address
import com.imaan.store.core.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ViewAddressesViewModel"
@HiltViewModel
class ViewAddressesViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ViewAddressesUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event get() = _event.receiveAsFlow()

    fun onAddressSelected(address: Address) {
        // TODO: Make the Address as default address
        _state.update {
            it.copy(
                selectedAddress = address
            )
        }
    }

    fun onDeliverToAddress(address: Address) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }
            val orderModel = orderRepository.getOrderModel()
            orderModel?.let { order ->
                orderRepository.updateOrder(
                    orderModel = order.copy(
                        address = address
                    )
                )
                _event.send(
                    element = UiEvent.Success(null)
                )
                _state.update {
                    it.copy(
                        loading = false
                    )
                }
            }

        }
    }

}