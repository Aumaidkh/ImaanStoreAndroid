package com.imaan.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.offers.OfferModel
import com.imaan.util.toColor


@Composable
fun OfferCard(
    modifier: Modifier = Modifier,
    offer: OfferModel
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
                    text = offer.discount.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textMotion = TextMotion.Animated,
                        fontSize = 35.sp,
                    ),
                    color = offer.discountColor.hexString.toColor()
                )
                Text(
                    text = offer.title.value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = offer.titleColor.hexString.toColor(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = offer.description.value,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = offer.descriptionColor.hexString.toColor(),
                    )

                )
            }
        }
    }
}