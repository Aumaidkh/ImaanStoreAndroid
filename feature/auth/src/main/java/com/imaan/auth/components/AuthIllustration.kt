package com.imaan.auth.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun AuthIllustration(
    modifier: Modifier = Modifier,
    imageResId: Int
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = imageResId),
        contentDescription = "Login Logo",
        contentScale = ContentScale.FillWidth
    )
}