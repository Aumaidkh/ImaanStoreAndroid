package com.imaan.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.cart.components.CartItemComponent
import com.imaan.cart.components.EmptyCartView
import com.imaan.cart.components.TotalComponent
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.total.TotalModel


@Composable
internal fun CartScreen(
    paddingValues: PaddingValues = PaddingValues(),
    totals: TotalModel,
    onProceedToCheckOut: () -> Unit = {},
    cartItemModels: List<CartItemModel> = emptyList(),
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
    onContinueShoppingClick: () -> Unit = {}
) {
    // Calculate subtotal based on cart item quantities and prices

    if (cartItemModels.isEmpty()) {
        EmptyCartView(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            onActionClick = onContinueShoppingClick
        )
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CartContent(
                    modifier = Modifier.fillMaxWidth(),
                    items = cartItemModels,
                    onQuantityIncrease = onQuantityIncrease,
                    onQuantityDecrease = onQuantityDecrease,
                    totals = totals,
                    paddingValues = paddingValues,
                    onProceedToCheckOut = onProceedToCheckOut
                )
            }
        }
    }
}


@Composable
private fun CartContent(
    items: List<CartItemModel>,
    modifier: Modifier = Modifier,
    onQuantityDecrease: (CartItemModel) -> Unit,
    onQuantityIncrease: (CartItemModel) -> Unit,
    onProceedToCheckOut: () -> Unit,
    totals: TotalModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = modifier
            .imePadding(),
    ) {
        items(
            items = items,
            key = { item -> item.productModel?.id?.value.toString() }
        ) {
            CartItemComponent(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                cartItemModel = it,
                onQuantityDecrease = onQuantityDecrease,
                onQuantityIncrease = onQuantityIncrease,
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                thickness = 0.6.dp,
                color = MaterialTheme.colorScheme.onSecondary.copy(
                    alpha = 0.3f
                )
            )
        }

        item {
            TotalComponent(totals, paddingValues )
        }

        item {
            ImaanAppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                text = "Proceed to checkout",
                onClick = onProceedToCheckOut
            )
        }
    }
}

