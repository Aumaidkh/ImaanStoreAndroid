package com.imaan.design_system.components.buttons


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class ButtonType {
    Outlined,
    Filled
}

@Composable
fun ImaanAppButton(
    modifier: Modifier = Modifier,
    text: String = "Ok",
    loading: Boolean = false,
    onClick: () -> Unit = {},
    height: Dp = 50.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    foregroundColor: Color = MaterialTheme.colorScheme.onPrimary,
    type: ButtonType = ButtonType.Filled
) {
    when(type){
        ButtonType.Outlined -> {
            ImaanOutlinedButton(
                modifier = modifier,
                text = text,
                onClick = onClick,
                loading = loading,
                height = height,
                enabled = enabled,
                backgroundColor = backgroundColor,
                foregroundColor = foregroundColor
            )
        }
        ButtonType.Filled -> {
            ImaanFilledButton(
                modifier = modifier,
                text = text,
                onClick = onClick,
                loading = loading,
                height = height,
                enabled = enabled,
                backgroundColor = backgroundColor,
                foregroundColor = foregroundColor
            )
        }
    }
}

@Composable
internal fun ImaanOutlinedButton(
    modifier: Modifier = Modifier,
    text: String = "Ok",
    loading: Boolean = false,
    onClick: () -> Unit = {},
    height: Dp = 50.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    foregroundColor: Color = MaterialTheme.colorScheme.onPrimary,

    ) {
    OutlinedButton(
        modifier = modifier
            .height(height),
        onClick = { onClick() },
        enabled = !loading && enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = foregroundColor
        )
    ) {
        AnimatedVisibility(visible = loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(end = 32.dp)
                    .size(20.dp),
                color = backgroundColor,
                strokeWidth = 2.dp
            )
        }
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = foregroundColor
            )
        )
    }
}

@Composable
internal fun ImaanFilledButton(
    modifier: Modifier = Modifier,
    text: String = "Ok",
    loading: Boolean = false,
    onClick: () -> Unit = {},
    height: Dp = 50.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    foregroundColor: Color = MaterialTheme.colorScheme.primary,
    ) {
    Button(
        modifier = modifier
            .height(height),
        onClick = { onClick() },
        enabled = !loading && enabled,
        colors = ButtonDefaults.buttonColors(
            //disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
            containerColor = backgroundColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        AnimatedVisibility(visible = loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(end = 32.dp)
                    .size(20.dp),
                color = backgroundColor,
                strokeWidth = 2.dp
            )
        }
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = foregroundColor
            )
        )
    }
}

@Preview
@Composable
fun LoadingButtonPreview() {
    Column {
        ImaanFilledButton()
        Spacer(modifier = Modifier.height(30.dp))
        ImaanOutlinedButton()
    }
}

@Preview
@Composable
fun LoadingButtonPreviewLoading() {
    Column {
        ImaanFilledButton(loading = true)
        Spacer(modifier = Modifier.height(30.dp))
        ImaanOutlinedButton(loading = true)
    }
}