package com.imaan.store.design_system.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CircularIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = Color.Transparent,
    icon: @Composable () -> Unit = {}
){
    Surface(
        modifier = modifier
            .clip(shape = CircleShape)
            .clickable { onClick() },
        shape = CircleShape,
        color = containerColor
    ) {
        icon()
    }
}

@Preview
@Composable
fun CircularIconPreview(){
    CircularIcon()
}