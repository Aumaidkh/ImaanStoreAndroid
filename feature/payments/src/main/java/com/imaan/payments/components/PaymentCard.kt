package com.imaan.payments.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.payments.util.DebitCard
import com.imaan.payments.util.dummyCard

@Composable
fun PaymentCard(
    rotated: Boolean = false,
    card: DebitCard = dummyCard,
) {
    val context = LocalContext.current

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500), label = ""
    )


    Box(
        Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                },
            colors = CardDefaults.cardColors(
                containerColor = card.type.backgroundColor()
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        )
        {
            if (!rotated){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .graphicsLayer {
                            alpha = animateFront
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val model = ImageRequest
                            .Builder(context)
                            .data(card.type.iconResId())
                            .build()
                        Text(
                            text = card.type.cardName(),
                            fontSize = 18.sp
                        )
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp),
                            model = model,
                            contentDescription = "")
                    }
                    val chip = ImageRequest
                        .Builder(context)
                        .data(com.imaan.design_system.R.drawable.chip)
                        .build()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .size(64.dp),
                            model = chip,
                            contentDescription = "Chip"
                        )

                    }
                    Text(
                        text = card.formattedCardNumber,
                        fontSize = 28.sp,
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "CARDHOLDER NAME",
                                fontSize = 12.sp,
                            )
                            Text(
                                text = card.cardHolderName,
                                fontSize = 18.sp,
                            )
                        }
                        Spacer(modifier = Modifier
                            .weight(0.4f))
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "VALID THRU",
                                fontSize = 12.sp,
                            )
                            Text(
                                text = card.formattedExpiry,
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = -180f
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 4.dp),
                        text = "For customer service call 1800 XXXX XXXX or 1234123412",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .background(color = Color.Black))

                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 18.dp)
                            .background(color = Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 8.dp),
                            text = card.secureCvv,
                            fontSize = 13.sp,
                            textAlign = TextAlign.End,
                            color = Color.Black
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 24.dp),
                        text = "This debit card is the property of the bank and it's use is governed by the terms and conditions of the Bank's Debit Card Agreement. It must be returned to the Bank on Request. If found please return to the nearest branch of the Bank.",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White.copy(alpha = 0.7f),
                        lineHeight = 12.sp
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                        text = "24X7 toll free number 1800 XXXX XXXX (for customers of India). International customers can call +1 XXXXXXXXXX",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White.copy(alpha = 0.7f),
                        lineHeight = 12.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentCardPreview() {
    PaymentCard()
}