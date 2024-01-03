package com.imaan.store.design_system.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    text: String = "Ok",
    loading: Boolean = false,
    onClick: () -> Unit = {},
    height: Dp = 50.dp,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .height(height),
        onClick = { onClick() },
        enabled = !loading && enabled,
        colors = ButtonDefaults.buttonColors(
            //disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        AnimatedVisibility(visible = loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(end = 32.dp)
                    .size(20.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        }
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Preview
@Composable
fun LoadingButtonPreview() {
    LoadingButton()
}

@Preview
@Composable
fun LoadingButtonPreviewLoading() {
    LoadingButton(loading = true)
}