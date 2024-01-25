package com.imaan.payments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.payments.components.CashOnDeliveryPaymentLayout
import com.imaan.payments.components.DebitCardPaymentLayout
import com.imaan.payments.components.PaymentModeSelector
import com.imaan.payments.components.UpiPaymentLayout

@Composable
fun PaymentScreen(
    state: PaymentScreenUiState = PaymentScreenUiState(),
    onCardHolderNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onCardExpiryChange: (String) -> Unit,
    onCvvChange: (String) -> Unit,
    onPaymentTypeClick: (PaymentMode) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
    onPay: () -> Unit
) {
    val scrollState = rememberScrollState()
    PaymentScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .verticalScroll(scrollState),
        state = state,
        onCvvChange = onCvvChange,
        onCardExpiryChange = onCardExpiryChange,
        onCardHolderNameChange = onCardHolderNameChange,
        onCardNumberChange = onCardNumberChange,
        onPaymentTypeClick = onPaymentTypeClick,
        onPay = onPay
    )

}

@Composable
fun PaymentScreenContent(
    modifier: Modifier = Modifier,
    state: PaymentScreenUiState = PaymentScreenUiState(),
    onCardHolderNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onCardExpiryChange: (String) -> Unit,
    onCvvChange: (String) -> Unit,
    onPaymentTypeClick: (PaymentMode) -> Unit,
    onPay: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
    ) {
        AnimatedVisibility(visible = state.paymentMode is Card) {
            DebitCardPaymentLayout(
                state = state,
                onCardHolderNameChange = onCardHolderNameChange,
                onCardNumberChange = onCardNumberChange,
                onCardExpiryChange = onCardExpiryChange,
                onCvvChange = onCvvChange,
                onPay = onPay
            )
        }

        AnimatedVisibility(visible = state.paymentMode is UPI) {
            UpiPaymentLayout(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth(),
            )
        }

        AnimatedVisibility(visible = state.paymentMode is CashOnDelivery) {
            CashOnDeliveryPaymentLayout(
                modifier = Modifier
                    .fillMaxWidth(),
                state = state
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = "Payment Mode",
            style = MaterialTheme.typography.titleMedium
        )
        PaymentModeSelector(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            onPaymentTypeClick = onPaymentTypeClick,
            activePaymentMode = state.paymentMode
        )

    }
}


@Preview
@Composable
fun CashOnDeliveryPaymentLayoutPreview() {
    CashOnDeliveryPaymentLayout()
}
