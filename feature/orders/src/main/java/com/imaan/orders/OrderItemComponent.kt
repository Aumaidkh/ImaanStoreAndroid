package com.imaan.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.CircularAsyncImage
import com.imaan.design_system.components.views.RoundedAsyncImage
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders

@OptIn(ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun OrderItemComponent(
    modifier: Modifier = Modifier,
    order: OrderModel = getDummyOrders(1)[0],
    onClick: (OrderModel) -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        onClick = {
            onClick(order)
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = order.stringId,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = order.totalAmount.inRupees,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundedAsyncImage(
                    modifier = Modifier
                        .size(70.dp),
                    imageURL = order.cartItems.firstOrNull()?.productModel?.imageUrl,
                    shape = MaterialTheme.shapes.medium
                )
                Text(
                    text = "${order.cartItems.size} Items",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.7f
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.timestamp.formattedTimeStamp(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.4f
                    )
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            FlowRow() {
                order.cartItems.takeLast(3).forEach {
                    Text(
                        text = it.productModel.title.value.substring(
                            0,
                            6
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                }
            }
            Text(
                text = order.address.readableAddress,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            )

        }
    }
}

@Preview
@Composable
fun OrderItemComponentPreview() {
    OrderItemComponent()
}