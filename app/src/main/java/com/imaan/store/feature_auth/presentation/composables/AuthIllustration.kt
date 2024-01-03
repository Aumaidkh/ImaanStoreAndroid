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
    density: Density,
    imageResId: Int
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = if (density == Density.HighDensity) 82.dp else 10.dp),
        painter = painterResource(id = imageResId),
        contentDescription = "Login Logo",
        contentScale = ContentScale.FillWidth
    )
}