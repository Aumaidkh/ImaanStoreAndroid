package com.imaan.design_system.components.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.buttons.ImaanAppButton


@Composable
fun ImaanEmptyView(
    modifier: Modifier = Modifier,
    resId: Int = com.imaan.resources.R.drawable.ic_person,
    title: String = "",
    message: String = "",
    iconTint: Color = MaterialTheme.colorScheme.primary,
    actionButtonText: String? = null,
    onActionButtonClick: () -> Unit = {}
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .padding(2.dp),
            painter = painterResource(id = resId),
            contentDescription = "",
            tint = iconTint
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        actionButtonText?.let {
            Spacer(modifier = Modifier.height(48.dp))
            ImaanAppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    ),
                text = actionButtonText,
                onClick = onActionButtonClick
            )
        }
    }
}