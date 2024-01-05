package com.imaan.store.feature_auth.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imaan.store.feature_auth.presentation.login.Density

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