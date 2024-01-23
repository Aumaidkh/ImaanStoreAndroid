package com.imaan.payments.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.payments.PaymentScreenUiState

private const val TAG = "CashOnDeliveryPaymentLa"

@Composable
fun CashOnDeliveryPaymentLayout(
    modifier: Modifier = Modifier,
    state: PaymentScreenUiState = PaymentScreenUiState()
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Order now",
            style = MaterialTheme.typography.titleMedium
        )

        Surface(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .padding(vertical = 12.dp),
            onClick = {  },
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Shipping to: ${state.address.readableAddress}",
                    style = MaterialTheme.typography.titleMedium
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Subtotal (${state.cartItems.size} item):",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = state.subtotal.inRupees,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Delivery:",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = state.deliveryCharges.inRupees,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Order Total:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = state.totalAmount.inRupees,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
        Surface(
            modifier = modifier,
            onClick = {  },
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Deliver to: ",
                    style = MaterialTheme.typography.titleMedium
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    text = state.address.fullName.value,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = state.address.readableAddress,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = "Place Order"
        )
    }
}