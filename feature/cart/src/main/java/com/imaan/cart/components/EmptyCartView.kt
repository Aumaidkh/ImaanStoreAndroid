package com.imaan.cart.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.design_system.components.views.ImaanEmptyView

@Composable
fun EmptyCartView(
    modifier: Modifier,
    onActionClick: () -> Unit

) {
    ImaanEmptyView(
        modifier = modifier,
        message = "Time to Explore and Fill it Up!",
        resId = com.imaan.design_system.R.drawable.ic_cart,
        title = "Empty Cart? Let's Start Shopping!",
        actionButtonText = "Continue Shopping",
        onActionButtonClick = onActionClick
    )
}