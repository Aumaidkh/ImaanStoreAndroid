package com.imaan.cart.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.components.EmptyView

@Composable
fun EmptyCartView(
    modifier: Modifier
) {
    EmptyView(
        modifier = modifier,
        message = "Time to Explore and Fill it Up!",
        resId = com.imaan.resources.R.drawable.ic_cart,
        title = "Empty Cart? Let's Start Shopping!"
    )
}