package com.imaan.payments.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.payments.Card
import com.imaan.payments.PaymentMode
import com.imaan.payments.paymentModes


@Composable
fun PaymentModeSelector(
    modifier: Modifier = Modifier,
    activePaymentMode: PaymentMode = Card,
    onPaymentTypeClick: (PaymentMode) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        paymentModes.forEach { paymentMode ->
            PaymentOption(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                paymentOption = paymentMode,
                onClick = {
                    onPaymentTypeClick(it)
                },
                active = activePaymentMode == paymentMode
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentOption(
    modifier: Modifier = Modifier,
    paymentOption: PaymentMode = Card,
    onClick: (PaymentMode) -> Unit = {},
    active: Boolean = false
) {
    val model = ImageRequest
        .Builder(LocalContext.current)
        .data(paymentOption.iconResId)
        .build()
    Surface(
        modifier = modifier,
        onClick = { onClick(paymentOption) },
        border = BorderStroke(
            width = if (active) 0.9.dp else 0.dp,
            color = if (active) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent
        ),
        shape = MaterialTheme.shapes.small,
        shadowElevation = 1.4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(36.dp),
                contentScale = ContentScale.Fit,
                model = model,
                contentDescription = ""
            )
            Text(
                text = paymentOption.label,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}