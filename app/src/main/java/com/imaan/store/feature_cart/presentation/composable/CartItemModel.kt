package com.imaan.store.feature_cart.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.store.R
import com.imaan.store.design_system.composables.CircularIcon
import java.util.UUID

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartItemModel: CartItemModel = CartItemModel(),
    onItemClick: () -> Unit = {},
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
) {
    val context = LocalContext.current

    Surface(
        tonalElevation = 0.01.dp,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            val model = ImageRequest.Builder(context)
                .data(R.drawable.dummy_pic)
                .build()
            AsyncImage(
                modifier = Modifier
                    .clip(
                        shape = MaterialTheme.shapes.small
                    )
                    .size(120.dp),
                model = model,
                contentDescription = "item_image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cartItemModel.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    CircularIcon(
                        onClick = onItemClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "")
                    }
                }

                Text(
                    text = cartItemModel.description,
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuantityChanger(
                        quantity = cartItemModel.itemQuantity,
                        onDecreaseClick = { onQuantityDecrease(cartItemModel) },
                        onIncreaseClick = { onQuantityIncrease(cartItemModel) }
                    )

                    Text(
                        text = cartItemModel.price,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    }


}

data class CartItemModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Flowers",
    val image: String = "",
    val description: String = "It's spines don't grow",
    private val basePrice: Int = 10,
    var quantity: Int = 1
) {
    val price
        get() =
            "$${quantity * basePrice}"

    val itemQuantity get() = quantity

    val totalPrice get() =
        quantity * basePrice

}
