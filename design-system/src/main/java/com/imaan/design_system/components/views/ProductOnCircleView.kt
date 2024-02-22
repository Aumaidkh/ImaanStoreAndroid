package com.imaan.design_system.components.views

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.net.URL
import kotlin.random.Random

fun generateRandomLightColor(seed: Int): Color {
    val random = Random(seed)

    // Generate random values for the RGB components
    val red = random.nextInt(128, 256) / 255f // 128 to 255
    val green = random.nextInt(128, 256) / 255f
    val blue = random.nextInt(128, 256) / 255f

    // Create the Color object
    return Color(red, green, blue)
}

@Composable
fun ProductOnCircleView(
    modifier: Modifier = Modifier,
    imageUrl: URL? = null,
    color: Color = Color.Cyan,
    imageSize: Dp = 150.dp
) {
    val imageRequest = ImageRequest
        .Builder(LocalContext.current)
        .data(imageUrl.toString())
        .build()
    AsyncImage(
        modifier = modifier
            .size(imageSize)
            .drawBehind {
                val width = this.size.width * 0.4f
                drawCircle(
                    radius = width,
                    color = color
                )
                drawCircle(
                    color = Color.White.copy(
                        alpha = 0.6f
                    ),
                    style = Stroke(
                        width = 3f
                    ),
                    radius = this.size.width * 0.35f
                )

            }
            .padding(start = 15.dp, top = 20.dp),
        model = imageRequest,
        contentDescription = "",
        contentScale = ContentScale.Fit
    )

}

@Preview
@Composable
fun ProductOnCircleViewPreview() {
    ProductOnCircleView()
}