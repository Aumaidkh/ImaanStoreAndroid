package com.imaan.orders

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.ImaanEmptyView
import com.imaan.order.OrderModel

@Composable
fun OrderHistoryScreen(
    uiState: OrdersUiState = OrdersUiState(),
    paddingValues: PaddingValues = PaddingValues(),
    onOrderClick: (OrderModel) -> Unit
) {
    if (uiState.orders.isEmpty()) {
        EmptyOrdersHistoryView(modifier = Modifier.fillMaxSize())
    } else {
        OrderHistoryFeed(
            paddingValues,
            uiState,
            onOrderClick
        )
    }
}

@Composable
fun EmptyOrdersHistoryView(
    modifier: Modifier
) {
    ImaanEmptyView(
        modifier = modifier,
        message = "Explore our offerings and place your first order today. Start filling your cart with delightful choices!",
        resId = R.drawable.heavy_box,
        title = "No orders yet.",
    )
}

@Composable
private fun OrderHistoryFeed(
    paddingValues: PaddingValues,
    uiState: OrdersUiState,
    onOrderClick: (OrderModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        items(
            items = uiState.orders,
        ) {
            OrderItemComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                order = it,
                onClick = onOrderClick,
            )
        }
    }
}

