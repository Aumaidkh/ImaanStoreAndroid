package com.imaan.addresses.view_addresses


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.addresses.Address
import com.imaan.order.IOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ViewAddressesViewModel"
@HiltViewModel
class ViewAddressesViewModel @Inject constructor(
    private val orderRepository: IOrderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ViewAddressesUiState())
    val state = _state.asStateFlow()


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
            val orderModel = orderRepository.unplacedOrder()
            orderModel?.let { order ->
                orderRepository.updateOrder(
                    orderModel = order.copy(
                        address = address
                    )
                )
                _state.update {
                    it.copy(
                        loading = false,
                        deliveryAddress = address
                    )
                }
            }

        }
    }

    fun onErrorShown(){
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun onDeliveryAddressHandled(){
        _state.update {
            it.copy(
                deliveryAddress = null
            )
        }
    }

}