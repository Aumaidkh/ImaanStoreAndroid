package com.imaan.orders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.components.OrderStatusComponent
import com.imaan.components.OrderTotalComponent
import com.imaan.design_system.components.views.SectionCardView
import com.imaan.details.OrderItem
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItemComponent(
    modifier: Modifier = Modifier,
    order: OrderModel = getDummyOrders(1)[0],
    onClick: (OrderModel) -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .padding(vertical = 12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.1.dp
        ),
        onClick = { onClick(order) }) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            bottom = 12.dp
                        ),
                    text = order.stringId,
                    style = MaterialTheme.typography.titleSmall
                )
                OrderStatusComponent(status = order.status)
            }
            AnimatedVisibility(visible = !expanded) {
                order.cartItems.first().also {
                    OrderItem(
                        modifier = Modifier.fillMaxWidth(),
                        cartItem = it,
                        imageSize = 80.dp
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                FullOrderContent(order = order)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = order.timestamp.formattedTimeStamp(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.4f
                        )
                    )
                )
                TextButton(onClick = { expanded = !expanded }) {
                    Text(text = if (!expanded) "See More" else "See less")
                }
            }
        }
    }
}

@Composable
fun FullOrderContent(
    order: OrderModel
) {
    Column {
        order.cartItems.forEach { item ->
            OrderItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                cartItem = item,
                imageSize = 50.dp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Shipping Address",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = order.address.fullName.value,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.address.phoneNumber.value,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.address.readableAddress,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun OrderItemComponentPreview() {
    OrderItemComponent()
}