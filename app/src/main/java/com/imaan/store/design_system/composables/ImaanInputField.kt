package com.imaan.store.design_system.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanInputField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    error: String? = null,
    iconResId: Int? = null,
    maxLength: Int? = null,
    label: String? = null,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done
) {
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            maxLength?.let { length ->
                if (it.length <= length) {
                    onValueChange(it)
                    if (it.length == length) {
                        focusManager.clearFocus()
                    }
                }
            } ?: onValueChange(it)
        },
        label = {
            label?.let {
                Text(
                    text = it
                )
            }
        },
        isError = error != null,
        supportingText = {
            AnimatedVisibility(visible = error != null) {
                error?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.error
                        )
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            iconResId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = ""
                )
            }
        },
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 18.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}

@Preview
@Composable
fun ImaanInputFieldPreview() {
    ImaanInputField()
}