package com.imaan.store.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.net.URL

@Composable
fun CircularImage(
    modifier: Modifier,
    resId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .clickable {
                onClick()
            }
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .clip(
                    shape = CircleShape
                ),
            painter = painterResource(id = resId),
            contentDescription = ""
        )
    }
}