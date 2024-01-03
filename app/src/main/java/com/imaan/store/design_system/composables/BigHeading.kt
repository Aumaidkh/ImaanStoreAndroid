package com.imaan.store.design_system.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BigHeading(
    text: String,
    tag: String
) {
    Text(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .semantics {
                testTag = tag
            },
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )
    )
}