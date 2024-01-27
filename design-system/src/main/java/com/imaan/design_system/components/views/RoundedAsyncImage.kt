package com.imaan.design_system.components.views


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.net.URL

@Composable
fun RoundedAsyncImage(
    modifier: Modifier,
    imageURL: URL?,
    shape: CornerBasedShape
) {
    val request = ImageRequest
        .Builder(LocalContext.current)
        .data(imageURL.toString())
        .build()
    Box(
        modifier = modifier
            .clip(shape = shape),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = shape
                ),
            model = request,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}