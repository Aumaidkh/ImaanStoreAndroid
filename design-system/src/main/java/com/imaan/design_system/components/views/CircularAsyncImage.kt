package com.imaan.design_system.components.views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
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
fun CircularAsyncImage(
    modifier: Modifier,
    imageURL: URL?,
    onClick: () -> Unit
) {
    val request = ImageRequest
        .Builder(LocalContext.current)
        .data(imageURL.toString())
        .build()
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(
                    shape = CircleShape
                ),
            model = request,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}