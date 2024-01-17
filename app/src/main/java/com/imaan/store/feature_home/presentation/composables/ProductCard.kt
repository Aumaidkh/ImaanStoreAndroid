package com.imaan.store.feature_home.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.store.R
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.core.domain.model.dummyProduct
import com.imaan.store.design_system.ui.theme.ImaanTheme

enum class ProductCardVariant {
    SMALL
}

@Composable
fun ProductCard(
    variant: ProductCardVariant = ProductCardVariant.SMALL
) {
    when {
        variant == ProductCardVariant.SMALL -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCardSmall(
    modifier: Modifier = Modifier,
    product: ProductModel = dummyProduct,
    imageRequest: ImageRequest = ImageRequest.Builder(LocalContext.current).data("").build(),
    onClick: (ProductModel) -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = { onClick(product) },
        colors = CardDefaults.cardColors(

        )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = MaterialTheme.shapes.medium
                        ),
                    model = imageRequest,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    lineHeight = 18.sp

                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = product.price.inRupees,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CircularIconButton(
                        iconResId = R.drawable.ic_cart,
                        onClick = {
                           // addToCart(product)
                            onAddToCart(product)
                        }
                    )

                }
            }

            CircularIconButton(
                modifier = Modifier
                    .padding(12.dp),
                iconResId = R.drawable.ic_favorite,
                containerColor = Color.Transparent,
                onClick = {

                }
            )
        }

    }
}

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "Cart",
            tint = tint
        )
    }
}

@Preview
@Composable
fun ProductCardSmallPreview() {
    ImaanTheme {
        ProductCardSmall()
    }
}