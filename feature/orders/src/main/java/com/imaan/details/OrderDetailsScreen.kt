package com.imaan.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.components.OrderStatusComponent
import com.imaan.components.OrderTotalComponent
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.design_system.components.views.SectionCardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    uiState: OrderDetailsUiState = OrderDetailsUiState(),
    paddingValues: PaddingValues = PaddingValues()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.1.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = uiState.order?.stringId ?: "",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                        uiState.order?.status?.let {
                            OrderStatusComponent(
                                status = it
                            )
                        }
                    }
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 24.dp),
                        text = uiState.order?.timestamp?.formattedTimeStamp() ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 20.dp),
                        color = Color.LightGray.copy(
                            alpha = 0.3f
                        )
                    )

                    uiState.user?.let {
                        UserComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            user = uiState.user
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 20.dp),
                        color = Color.LightGray.copy(
                            alpha = 0.3f
                        )
                    )

                    ImaanAppButton(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        text = "Continue Shopping",
                        foregroundColor = MaterialTheme.colorScheme.primary
                    )

                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionCardView(label = "Items Purchased (${uiState.order?.cartItems?.size})") {
                uiState.order?.cartItems?.forEach {
                    OrderItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        cartItem = it
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionCardView(label = "Total Amount") {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    OrderTotalComponent(
                        label = "Subtotal",
                        amount = "${uiState.order?.orderSubtotal?.inRupees}"
                    )
                    OrderTotalComponent(
                        label = "Delivery Charges",
                        amount = "+${uiState.order?.deliveryCharges?.inRupees}"
                    )
                    OrderTotalComponent(
                        label = "Discount",
                        amount = "-${uiState.order?.discount?.inRupees}"
                    )
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        color = Color.LightGray.copy(
                            alpha = 0.4f
                        )
                    )
                    OrderTotalComponent(
                        label = "Grand Total",
                        amount = "${uiState.order?.grandTotal?.inRupees}"
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            SectionCardView(label = "Shipping Address") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = uiState.order?.address?.fullName?.value ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = uiState.order?.address?.phoneNumber?.value ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = uiState.order?.address?.readableAddress ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun OrderDetailsScreenPreview() {
    OrderDetailsScreen()
}