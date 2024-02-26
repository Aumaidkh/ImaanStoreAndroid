package com.imaan.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.cart.CartItemModel
import com.imaan.design_system.components.views.ProductOnCircleView
import com.imaan.design_system.components.views.generateRandomLightColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemComponent(
    modifier: Modifier = Modifier,
    cartItemModel: CartItemModel = CartItemModel(),
    onItemClick: () -> Unit = {},
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
) {
    Card(
        onClick = { onItemClick() },
        modifier = modifier.shadow(
            elevation = 4.dp,
            shape = MaterialTheme.shapes.extraLarge,
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductOnCircleView(
                modifier = Modifier
                    .size(110.dp),
                imageUrl = cartItemModel.productModel.image.thumbnail,
                color = generateRandomLightColor(2)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Top
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = cartItemModel.totalPrice.inRupees,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    )
                )
            }
            QuantityChanger(
                modifier = Modifier
                    .fillMaxWidth(1f),
                onQuantityDecrease = {
                    onQuantityDecrease(cartItemModel)
                },
                onQuantityIncrease = {
                    onQuantityIncrease(cartItemModel)
                },
                currentQuantity = cartItemModel.quantity
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantityChanger(
    modifier: Modifier,
    currentQuantity: Int,
    onQuantityIncrease: () -> Unit,
    onQuantityDecrease: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            onClick = {
                      onQuantityDecrease()
            },
            shape = CircleShape
        ) {
            Icon(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = com.imaan.design_system.R.drawable.ic_minus),
                contentDescription = ""
            )
        }
        Text(text = currentQuantity.toString())
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            shape = CircleShape,
            onClick = { onQuantityIncrease() }
        ) {
            Icon(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = com.imaan.design_system.R.drawable.ic_plus),
                contentDescription = ""
            )
        }
    }
}

