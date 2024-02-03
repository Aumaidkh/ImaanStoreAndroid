package com.imaan.product_details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.products.SizeVariant


@Composable
fun SizeViewerComponent(
    modifier: Modifier,
    selectedSize: SizeVariant? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = selectedSize?.size ?: "",
        onValueChange = {},
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = ""
            )
        },
        enabled = false,
    )
}