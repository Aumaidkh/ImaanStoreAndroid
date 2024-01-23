package com.imaan.addresses.view_addresses.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.components.EmptyView

@Composable
fun NoAddressesView(
    modifier: Modifier = Modifier
) {
    EmptyView(
        modifier = modifier,
        resId = com.imaan.resources.R.drawable.ic_location,
        title = "Add Your First Address!",
        message = "Unlock the convenience of delivery by adding an address."
    )
}