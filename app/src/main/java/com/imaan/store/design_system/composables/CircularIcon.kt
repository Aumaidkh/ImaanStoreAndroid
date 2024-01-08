package com.imaan.store.design_system.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imaan.store.R

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
        color = containerColor,
        tonalElevation = 10.dp
    ) {
        icon()
    }
}

@Composable
fun CircularIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = Color.Transparent,
    iconResId: Int = R.drawable.home
){
    Surface(
        modifier = modifier
            .clip(shape = CircleShape)
            .clickable { onClick() },
        shape = CircleShape,
        color = containerColor,
        tonalElevation = 8.8.dp
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp),
            painter = painterResource(id = iconResId),
            contentDescription = "icon"
        )
    }
}
