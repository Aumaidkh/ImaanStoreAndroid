package com.imaan.design_system.components.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.design_system.components.buttons.CircularIcon
import com.imaan.products.ProductModel
import com.imaan.products.dummyProduct
import kotlin.random.Random


enum class ProductCardSize {
    Small,
    Large
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductModel = dummyProduct,
    onClick: (ProductModel) -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {},
    size: ProductCardSize = ProductCardSize.Small,
    elevation: CardElevation = CardDefaults.elevatedCardElevation()
){
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(product.primaryImage?.thumbnail.toString())
        .build()
    when(size){
        ProductCardSize.Small -> ProductCardSmall(
            modifier = modifier,
            product = product,
            imageRequest = imageRequest,
            onClick = onClick,
            onAddToCart = onAddToCart,
            elevation = elevation
        )
        ProductCardSize.Large -> ProductCardSmall(
            modifier = modifier,
            product = product,
            imageRequest = imageRequest,
            onClick = onClick,
            onAddToCart = onAddToCart
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductCardSmall(
    modifier: Modifier = Modifier,
    product: ProductModel = dummyProduct,
    imageRequest: ImageRequest = ImageRequest.Builder(LocalContext.current).data("").build(),
    onClick: (ProductModel) -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {},
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    liked: Boolean = false
) {
    Card(
        modifier = modifier,
        onClick = { onClick(product) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = elevation
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                ProductOnCircleView(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    imageUrl = product.imageUrl,
                    color = generateRandomLightColor(Random.nextInt(10))
                )
                Text(
                    text = product.title.value,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    lineHeight = 18.sp

                )
                Text(
                    text = product.description.value,
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
                    CircularIcon(
                        iconResId = com.imaan.resources.R.drawable.ic_cart,
                        onClick = {
                            // addToCart(product)
                            onAddToCart(product)
                        }
                    )

                }
            }

            CircularIcon(
                modifier = Modifier
                    .padding(12.dp),
                iconResId = com.imaan.resources.R.drawable.ic_favorite,
                containerColor = Color.White,
                onClick = {

                },
                tint = Color.Red
            )
        }

    }
}