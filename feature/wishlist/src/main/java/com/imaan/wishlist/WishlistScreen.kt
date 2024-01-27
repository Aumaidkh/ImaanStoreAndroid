package com.imaan.wishlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.ProductCard

@Composable
fun WishlistScreen(
    uiState: WishlistScreenUiState = WishlistScreenUiState(),
    paddingValues: PaddingValues = PaddingValues()
) {
    if (uiState.products.isEmpty()) {
        EmptyWishlistView(modifier = Modifier.fillMaxSize())
    } else {
        WishlistFeed(
            uiState = uiState,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun WishlistFeed(
    uiState: WishlistScreenUiState,
    paddingValues: PaddingValues
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        items(
            items = uiState.products
        ) {
            ProductCard(
                product = it
            )
        }
    }
}