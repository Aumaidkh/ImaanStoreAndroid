package com.imaan.design_system.components.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionCardView(
    modifier: Modifier = Modifier,
    label: String,
    cardElevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 0.1.dp
    ),
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp),
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = cardElevation,
        ) {
            content()
        }
    }
}