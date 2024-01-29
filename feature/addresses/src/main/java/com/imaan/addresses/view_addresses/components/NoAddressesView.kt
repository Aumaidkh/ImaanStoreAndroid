package com.imaan.addresses.view_addresses.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.design_system.components.views.ImaanEmptyView

@Composable
fun NoAddressesView(
    modifier: Modifier = Modifier
) {
    ImaanEmptyView(
        modifier = modifier,
        resId = com.imaan.design_system.R.drawable.ic_location,
        title = "Add Your First Address!",
        message = "Unlock the convenience of delivery by adding an address."
    )
}