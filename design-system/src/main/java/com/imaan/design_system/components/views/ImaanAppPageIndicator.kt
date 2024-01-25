package com.imaan.design_system.components.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ImaanAppPageIndicator(
    modifier: Modifier = Modifier,
    maxPageCount: Int = 3,
    currentPageNumber: Int = 0,
    itemSize: Dp = 15.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxPageCount){
            PageItemCircle(
                isCurrent = currentPageNumber == it,
                size = itemSize
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun PageItemCircle(
    isCurrent: Boolean = false,
    size: Dp = 10.dp
) {
    Surface(
        modifier = Modifier
            .size(size),
        color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.2f
        ),
        shape = CircleShape,
    ) {

    }
}