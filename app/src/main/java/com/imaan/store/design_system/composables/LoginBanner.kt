package com.imaan.store.design_system.composables

import android.graphics.drawable.shapes.PathShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.R

@Composable
fun LoginBanner() {
//    Canvas(modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp)
//        .background(Color.White)
//    ) {
//        val height = size.height
//        val width = size.width
//        val path =   Path().apply {
//            moveTo(width * 1.0f, height * 0.4525f)
//            lineTo(width * 1.0f, height * 0.0f)
//            lineTo(width * 0.0f, height * 0.0f)
//            lineTo(width * 0.0f, height * 0.882f)
//            cubicTo(
//                width * 0.0f, height * 0.882f,
//                width * 0.25f, height * 1.0f,
//                width * 0.5f, height * 0.833f
//            )
//            cubicTo(
//                width * 0.75f, height * 0.667f,
//                width * 1.0f, height * 0.4525f,
//                width * 1.0f, height * 0.4525f
//            )
//            close()
//        }
//
//        drawPath(
//            path = path,
//            color = Color.Red,
//            style = Fill
//        )
//
//
//    }

    Image(
        painter = painterResource(id = R.drawable.masjid),
        contentDescription = "Clipped Image",
        modifier = Modifier
            .drawBehind {
                val height = size.height
                val width = size.width
                val path =   Path().apply {
                    moveTo(width * 1.0f, height * 0.4525f)
                    lineTo(width * 1.0f, height * 0.0f)
                    lineTo(width * 0.0f, height * 0.0f)
                    lineTo(width * 0.0f, height * 0.882f)
                    cubicTo(
                        width * 0.0f, height * 0.882f,
                        width * 0.25f, height * 1.0f,
                        width * 0.5f, height * 0.833f
                    )
                    cubicTo(
                        width * 0.75f, height * 0.667f,
                        width * 1.0f, height * 0.4525f,
                        width * 1.0f, height * 0.4525f
                    )
                    close()
                }

                drawPath(
                    path = path,
                    color = Color.Red,
                    style = Fill
                )
            }
            .size(600.dp) // Adjust size as needed
            .clipToBounds()
    )
}

@Composable
fun WavyIslamicIllustration() {
    Box(modifier = Modifier.fillMaxWidth()){
        val clippingPath = Path().apply {
            // Define the path for clipping
            // Adjusted to match the canvas size
            moveTo(200f, 90f)
            lineTo(200f, 0f)
            lineTo(0f, 0f)
            lineTo(0f, 160f)
            cubicTo(
                0f, 160f,
                50f, 190f,
                100f, 150f
            )
            cubicTo(
                150f, 110f,
                200f, 90f,
                200f, 90f
            )
            close()
        }
        
    }
}

@Preview
@Composable
fun LoginBannerPreview() {
    LoginBanner()
}