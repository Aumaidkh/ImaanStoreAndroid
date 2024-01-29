package com.imaan.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imaan.cart.CartItemModel
import com.imaan.design_system.components.views.ProductOnCircleView
import com.imaan.design_system.components.views.RoundedAsyncImage


@Composable
internal fun OrderItem(
    modifier: Modifier,
    cartItem: CartItemModel,
    imageSize: Dp = 80.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        ProductOnCircleView(
            modifier = Modifier.size(imageSize),
            imageUrl = cartItem.productModel.imageUrl,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = cartItem.productNameWithQuantity,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = cartItem.productModel.description.toShortDescription(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = cartItem.totalAmount.inRupees,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}