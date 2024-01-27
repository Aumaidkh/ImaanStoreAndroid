package com.imaan.tracking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.order.OrderModel

@Composable
fun TrackOrderScreen(
    uiState: OrderTrackingUiState = OrderTrackingUiState(),
    paddingValues: PaddingValues = PaddingValues(),
    onContactSeller: () -> Unit = {},
    onNeedInfoWithThisOrder: (OrderModel) -> Unit = {}
) {
    uiState.order?.let {
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
                EstimatedDeliveryDateView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    date = it.expectedDelivery
                )
            }

            item {
                SectionCardView(label = "Tracking") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        OrderStatus()
                        OrderStatus()
                        OrderStatus(showLine = false)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                SectionCardView(label = "Shipping Address") {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 24.dp,
                                vertical = 12.dp
                            )
                    ) {
                        Text(
                            text = it.address.fullName.value,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = it.address.readableAddress,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = it.address.phoneNumber.value,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCardView(label = "Package Items ${it.cartItems.size}") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        it.cartItems.forEach {
                            PackageProductComponent(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                product = it.productModel
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                SectionCardView(label = "Contact Information") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp,
                                vertical = 8.dp
                            )
                    ) {
                        TextButton(onClick = { onNeedInfoWithThisOrder(uiState.order) }) {
                            Text(
                                text = "Need Help with this order",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        TextButton(onClick = { onContactSeller() }) {
                            Text(
                                text = "Contact Seller",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }

        }
    } ?: NoActiveOrderView(
        modifier = Modifier
            .fillMaxSize()
    )

}

@Preview
@Composable
fun TrackOrderScreenPreview() {
    TrackOrderScreen()
}