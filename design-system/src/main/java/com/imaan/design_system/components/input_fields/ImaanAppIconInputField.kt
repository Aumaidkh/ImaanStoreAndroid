package com.imaan.design_system.components.input_fields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanInputFieldWithIcon(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    error: String? = null,
    iconResId: Int? = null,
    maxLength: Int? = null,
    placeHolder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    focusManager: FocusManager = LocalFocusManager.current,
    title: String? = null
) {
    Column(
        modifier = modifier
    ) {
        title?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
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
            placeholder = {
                placeHolder?.let {
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
            ),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanInputFieldWithIcon(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    error: String? = null,
    maxLength: Int? = null,
    placeHolder: String? = null,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    focusManager: FocusManager = LocalFocusManager.current,
    title: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
    ) {
        title?.let {
            Text(
                modifier = Modifier
                    .padding(bottom = 6.dp),
                text = it,
                style = MaterialTheme.typography.titleMedium.copy()
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = {
                maxLength?.let { length ->
                    if (it.length <= length) {
                        onValueChange(it)
                        if (it.length == length) {
                           // focusManager.clearFocus()
                        }
                    }
                } ?: onValueChange(it)
            },
            placeholder = {
                placeHolder?.let {
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
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            enabled = enabled
        )
    }
}
