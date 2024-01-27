package com.imaan.tracking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders

@Composable
fun OrderTrackingCard(
    modifier: Modifier = Modifier,
    order: OrderModel = getDummyOrders(1)[0]
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Tracking")
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.1.dp
            )
        ) {
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
}

@Preview
@Composable
fun OrderTrackingCardPreview() {
    OrderTrackingCard()
}

@Composable
fun OrderStatus(
    status: String = "Order Placed",
    showLine: Boolean = true
) {
    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(20.dp),
                shape = CircleShape,
                content = {},
                color = MaterialTheme.colorScheme.primary
            )
            if (showLine){
                Surface(
                    modifier = Modifier
                        .width(2.dp)
                        .height(60.dp),
                    content = {},
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = status,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "April 29 - 5:34 pm",
                style = MaterialTheme.typography.bodySmall.copy(
                    //                    color = MaterialTheme.colorScheme.onSurface.copy(
                    //                        //alpha = 1f
                    //                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun OrderStatusPreview() {
    OrderStatus()
}