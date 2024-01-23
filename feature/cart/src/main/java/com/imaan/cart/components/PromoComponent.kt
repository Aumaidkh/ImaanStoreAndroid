package com.imaan.cart.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun PromoComponent(
    modifier: Modifier = Modifier,
    promoValid: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        trailingIcon = {

        },
        placeholder = {
            Text(text = "APPLY PROMO")
        },
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.4f
            )
        )
    )
}