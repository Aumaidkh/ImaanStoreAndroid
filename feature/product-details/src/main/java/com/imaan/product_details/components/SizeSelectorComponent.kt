package com.imaan.product_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeSelectorComponent(
    modifier: Modifier,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        border = CardDefaults.outlinedCardBorder(
            enabled = isSelected
        ),
        onClick = { onClick() }
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = label
        )
    }
}

@PreviewLightDark
@Composable
fun SizeSelectorComponentPreview() {
    SizeSelectorComponent(
        modifier = Modifier,
        label = "Small",
        isSelected = false
    ) {

    }
}