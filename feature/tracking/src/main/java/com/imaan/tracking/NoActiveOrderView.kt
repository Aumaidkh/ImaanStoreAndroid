package com.imaan.tracking

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.imaan.design_system.components.views.ImaanEmptyView

@Composable
fun NoActiveOrderView(
    modifier: Modifier = Modifier
) {
    ImaanEmptyView(
        modifier = modifier,
        title = "No Active Orders!",
        message = "Your Order Queue is Empty – Time to Add a Dash of Delight!",
        resId = com.imaan.design_system.R.drawable.delivery_truck,
        iconTint = MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground = true)
@Composable
fun NoActiveOrderPreview() {
    NoActiveOrderView(
        Modifier
            .fillMaxSize()
    )
}