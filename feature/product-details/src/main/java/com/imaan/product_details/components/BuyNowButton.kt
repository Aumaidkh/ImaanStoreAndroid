package com.imaan.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imaan.design_system.R


@Composable
fun BuyNowButton(
    onBuyNowClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {}
) {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clickable { onAddToCartClick() }
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "1",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
                    .clickable { onBuyNowClick() }
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Buy Now",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}