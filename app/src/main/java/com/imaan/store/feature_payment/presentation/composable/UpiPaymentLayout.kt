package com.imaan.store.feature_payment.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imaan.store.design_system.composables.ImaanInputField
import com.imaan.store.design_system.composables.LoadingButton


@Composable
fun UpiPaymentLayout(
    modifier: Modifier = Modifier,
    upiId: String = "",
    onUpiIdChange: (String) -> Unit = {},
    buttonText: String = "Verify"
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Pay Using UPI",
            style = MaterialTheme.typography.titleLarge
        )

        ImaanInputField(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            title = "Name",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            value = upiId,
            onValueChange = onUpiIdChange,
            visualTransformation = VisualTransformation.None
        )

        LoadingButton(
            text = buttonText,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth()
        )
    }
}
