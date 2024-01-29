package com.imaan.tracking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.CircularAsyncImage
import com.imaan.design_system.components.views.ProductOnCircleView
import com.imaan.order.OrderModel
import com.imaan.order.getDummyOrders
import com.imaan.products.ProductModel


@Composable
fun OrderProductsView(
    modifier: Modifier = Modifier,
    order: OrderModel = getDummyOrders(1)[0]
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Package Items ${order.cartItems.size}",
            style = MaterialTheme.typography.titleMedium
        )
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
                order.cartItems.forEach {
                    PackageProductComponent(
                        modifier = Modifier
                            .fillMaxWidth(),
                        product = it.productModel
                    )
                }
            }
        }
    }
}

@Composable
fun PackageProductComponent(
    modifier: Modifier,
    product: ProductModel
) {
    Row(
        modifier = modifier
            .padding(8.dp)
    ) {
        ProductOnCircleView(
            modifier = Modifier
                .size(60.dp),
            imageUrl = product.imageUrl,
        )
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.title.value,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = product.description.toShortDescription(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.4f
                    )
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.price.inRupees,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

