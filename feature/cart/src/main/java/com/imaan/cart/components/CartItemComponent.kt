package com.imaan.cart.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.cart.CartItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemComponent(
    modifier: Modifier = Modifier,
    cartItemModel: CartItemModel = CartItemModel(),
    onItemClick: () -> Unit = {},
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
    onRemove: (CartItemModel) -> Unit = {}
) {
    val context = LocalContext.current
    Card(onClick = { onItemClick() }, modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            val model = ImageRequest.Builder(context)
                .data(cartItemModel.productModel.imageUrl.toString())
                .build()
            AsyncImage(
                modifier = Modifier
                    .clip(
                        shape = MaterialTheme.shapes.small
                    )
                    .fillMaxHeight()
                    .size(140.dp),
                model = model,
                contentDescription = "item_image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        text = cartItemModel.productModel.title.value,
                        style = MaterialTheme.typography.titleMedium.copy(
                            lineHeight = 19.sp,
                            fontSize = 16.sp
                        ),
                        maxLines = 2
                    )
                    IconButton(onClick = { onRemove(cartItemModel) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.5f
                            )
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = cartItemModel.productModel.description.value,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cartItemModel.totalPrice.inRupees,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 18.sp
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedIconButton(
                            onClick = {
                                onQuantityDecrease(cartItemModel)
                            },
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.5f
                                )
                            )
                        ) {
                            Icon(painter = painterResource(id = com.imaan.resources.R.drawable.ic_minus), contentDescription = "")
                        }
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            text = cartItemModel.itemQuantity.toString()
                        )
                        OutlinedIconButton(
                            onClick = {
                                onQuantityIncrease(cartItemModel)
                            },
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(painter = painterResource(id = com.imaan.resources.R.drawable.ic_plus), contentDescription = "")
                        }
                    }
                }
            }
        }
    }
}