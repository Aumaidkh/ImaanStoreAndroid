package com.imaan.design_system.components.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyColumnSurfaceSection(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.03.dp,
    label: String? = null,
    horizontalPadding: Dp = 32.dp,
    verticalPadding: Dp = 24.dp,
    labelHorizontalPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        tonalElevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = verticalPadding)
                .padding(horizontal = horizontalPadding)
        ) {
            label?.let {
                Text(
                    modifier = Modifier.padding(horizontal = labelHorizontalPadding),
                    text = label,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}