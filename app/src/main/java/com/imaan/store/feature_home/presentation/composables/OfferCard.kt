package com.imaan.store.feature_home.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.store.core.domain.model.ID
import com.imaan.store.design_system.ui.theme.poppinsFontFamily
import java.net.URL
import java.util.Date

@Composable
fun OfferCard(
    modifier: Modifier = Modifier,
    offer: OfferModel = getDummyOffer().first()
) {
    Card(
        modifier = modifier
    ) {
        val model = ImageRequest
            .Builder(LocalContext.current)
            .data(offer.imageUrl.toString())
            .build()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                model = model,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = offer.discount.toString,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = offer.discountColor,
                        fontWeight = FontWeight.SemiBold,
                        textMotion = TextMotion.Animated,
                        fontSize = 35.sp,
                        fontFamily = poppinsFontFamily
                    )
                )
                Text(
                    text = offer.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = offer.titleColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = offer.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = offer.descriptionColor,
                    )

                )
            }
        }
    }
}

@Preview
@Composable
fun OfferCardPreview() {
    OfferCard()
}

data class OfferModel(
    val id: ID,
    val imageUrl: URL,
    val startDate: Date,
    val expiryDate: Date,
    val discount: Discount,
    val discountColor: Color = Color.White,
    val title: String,
    val titleColor: Color = Color.White,
    val description: String,
    val descriptionColor: Color = Color.White
)

@JvmInline
value class Discount(val value: Float){
    val toString get() =
        "${(value * 100).toInt()}% Off"
}

fun getDummyOffer(count: Int = 1): List<OfferModel> {
    val offers = mutableListOf<OfferModel>()
    repeat(count) {
        offers.add(
            OfferModel(
                id = ID(""),
                imageUrl = URL("https://images.pexels.com/photos/1279813/pexels-photo-1279813.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                startDate = Date(),
                expiryDate = Date(),
                discount = Discount(0.50f),
                title = "On all PRADA product",
                description = "Promo code WD400 to get 25% off on all PRADA product"
            )
        )
    }

    return offers
}
