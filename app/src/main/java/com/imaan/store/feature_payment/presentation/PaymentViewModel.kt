package com.imaan.store.feature_payment.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imaan.store.core.domain.OrderRepository
import com.imaan.store.core.domain.model.Amount
import com.imaan.store.feature_payment.domain.model.CardType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PaymentViewModel"
@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentScreenUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            orderRepository.orderFlow.collect { orderModel ->
                Log.d(TAG, "OrderModel: $orderModel")
                _state.update {
                    it.copy(
                        deliveryCharges = Amount.fromDouble(orderModel.deliveryCharges.value),
                        subtotal = Amount.fromDouble(orderModel.subtotalAmount.value),
                        totalAmount = Amount.fromDouble(orderModel.totalAmount.value),
                        address = orderModel.address,
                        cartItems = orderModel.cartItems
                    )
                }
            }
        }
    }

    fun onNameChange(value: String) {
        _state.update {
            it.copy(
                card = _state.value.card.copy(
                    name = value
                )
            )
        }
    }

    fun onCardNumberChange(value: String) {
        if (value.length > 16) {
            return
        }
        _state.update {
            it.copy(
                card = _state.value.card.copy(
                    type = parseCardType(value),
                    cardNo = value
                )
            )
        }
    }


    fun onExpiryChange(value: String) {
        _state.update {
            it.copy(
                card = _state.value.card.copy(
                    expiry = value
                )
            )
        }
    }

    fun onPaymentModeClick(paymentMode: PaymentMode) {
        _state.update {
            it.copy(
                paymentMode = paymentMode
            )
        }
    }

    fun onCvvChange(value: String) {
        if (value.length > 3) {
            return
        }
        _state.update {
            it.copy(
                card = _state.value.card.copy(
                    cvv = value
                )
            )
        }
    }

    private fun parseCardType(cardNumber: String): CardType {
        val mastercardPattern = "^5[1-5][0-9]{14}\$".toRegex()
        val visaPattern = "^4[0-9]{12}(?:[0-9]{3})?\$".toRegex()
        val rupayPattern = "^6[0-9]{15}\$".toRegex()

        return when {
            mastercardPattern.matches(cardNumber) -> CardType.MasterCard
            visaPattern.matches(cardNumber) -> CardType.Visa
            rupayPattern.matches(cardNumber) -> CardType.RuPay
            else -> CardType.RuPay
        }
    }

    fun placeOrder(){
        // TODO: Before submitting order request make sure to check the stock or all
        // The products that are going to be the part of the order
    }
}