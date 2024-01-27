package com.imaan.wishlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imaan.design_system.components.views.ImaanEmptyView

@Composable
fun EmptyWishlistView(
    modifier: Modifier
) {
    ImaanEmptyView(
        modifier = modifier,
        resId = R.drawable.wishlist_illustration,
        title = "Empty Wishlist",
        message = "Discover exciting products and add them to create your personalized collection"
    )
}