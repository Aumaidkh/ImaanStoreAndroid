package com.imaan.store.feature_manage_addresses.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddressInputField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    error: String? = null,
    maxLength: Int? = null,
    hint: String? = null,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    focusManager: FocusManager = LocalFocusManager.current,
    title: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    maxLines: Int = 1
){
    Column(
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        title?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.85f
                ),
                fontWeight = FontWeight.Medium
            )
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.4f
                    ),
                    shape = MaterialTheme.shapes.extraSmall
                ),
            value = value,
            onValueChange = {
                if(maxLength != null){
                    if (it.length<=maxLength){
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background
            ),
            shape = MaterialTheme.shapes.extraSmall,
            placeholder = {
                hint?.let{
                    Text(
                        text = hint,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.55f
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            enabled = enabled,
            visualTransformation = visualTransformation,
            isError = error != null,
            maxLines = maxLines
        )
    }
}

@Preview
@Composable
fun AddressInputFieldPreview(){
    AddressInputField()
}