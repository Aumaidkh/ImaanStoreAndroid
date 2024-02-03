package com.imaan.product_details.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.design_system.components.views.ProductCard
import com.imaan.design_system.components.views.ProductCardSize
import com.imaan.products.ProductModel


@Composable
fun ItemsYouMayLikeSection(
    modifier: Modifier = Modifier,
    items: List<ProductModel> = emptyList(),
    onItemClick: (ProductModel) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Items you may also like",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(items) {
                ProductCard(
                    modifier = Modifier
                        .width(200.dp)
                        .height(300.dp),
                    size = ProductCardSize.Small,
                    product = it,
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 1.dp
                    ),
                    onClick = onItemClick
                )
            }
        }
    }
}