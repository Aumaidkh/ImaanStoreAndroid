package com.imaan.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


@Composable
fun CircularIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = Color.Transparent,
    icon: @Composable () -> Unit = {},
    iconResId: Int?,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    iconResId?.let {
        CircularIconWithIconResId(
            modifier = modifier,
            onClick = onClick,
            containerColor = containerColor,
            iconResId = iconResId,
            tint = tint
        )
    } ?: run {
        CircularIconWithIconComposable(
            modifier = modifier,
            onClick = onClick,
            containerColor = containerColor,
            icon = icon
        )
    }
}

@Composable
private fun CircularIconWithIconComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color = Color.Transparent,
    icon: @Composable () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        icon()
    }
}


@Composable
private fun CircularIconWithIconResId(
    modifier: Modifier = Modifier,
    iconResId: Int,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "Cart",
            tint = tint
        )
    }
}