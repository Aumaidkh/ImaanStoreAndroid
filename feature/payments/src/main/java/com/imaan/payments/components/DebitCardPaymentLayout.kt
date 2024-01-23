package com.imaan.payments.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.design_system.components.input_fields.ImaanAppInputField
import com.imaan.payments.PaymentScreenUiState
import com.imaan.payments.util.CardExpiryVisualTransformer

@Composable
fun DebitCardPaymentLayout(
    state: PaymentScreenUiState,
    onCardHolderNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onCardExpiryChange: (String) -> Unit,
    onCvvChange: (String) -> Unit,
) {
    var showCardBack by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PaymentCard(
            rotated = showCardBack,
            card = state.card
        )

        CardForm(
            modifier = Modifier
                .padding(top = 12.dp),
            onCvvFocused = {
                showCardBack = it
            },
            state = state,
            onCvvChange = onCvvChange,
            onCardExpiryChange = onCardExpiryChange,
            onCardHolderNameChange = onCardHolderNameChange,
            onCardNumberChange = onCardNumberChange
        )
    }
}


@Composable
fun CardForm(
    modifier: Modifier = Modifier,
    onCvvFocused: (Boolean) -> Unit = {},
    state: PaymentScreenUiState = PaymentScreenUiState(),
    onCardHolderNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onCardExpiryChange: (String) -> Unit,
    onCvvChange: (String) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .imePadding()
    ) {
        ImaanAppInputField(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Name",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            value = state.card.name,
            onValueChange = onCardHolderNameChange,
            visualTransformation = VisualTransformation.None
        )
        ImaanAppInputField(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Card Number",
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            value = state.card.cardNo,
            onValueChange = onCardNumberChange,
        )
        Row(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImaanAppInputField(
                modifier = Modifier
                    .fillMaxWidth(0.55f),
                title = "Expiry",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                value = state.card.expiry,
                onValueChange = onCardExpiryChange,
                visualTransformation = CardExpiryVisualTransformer(),
                maxLength = 4
            )
            Spacer(modifier = Modifier.weight(1f))
            ImaanAppInputField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .onFocusChanged {
                        onCvvFocused(it.isFocused)
                    },
                title = "CVV",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                value = state.card.cvv,
                onValueChange = {
                    onCvvChange(it)
                    if (it.length == 3) {
                        localFocusManager.clearFocus()
                    }
                },
                visualTransformation = PasswordVisualTransformation()
            )

        }

        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Pay ${state.totalAmount.inRupees}",
            loading = state.loading
        )
    }
}