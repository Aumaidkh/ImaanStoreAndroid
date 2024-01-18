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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.cart.components.CartItemComponent
import com.imaan.cart.components.EmptyCartView
import com.imaan.cart.components.TotalComponent
import com.imaan.components.ImaanTopAppBar
import com.imaan.components.LoadingButton
import com.imaan.total.TotalModel


@Composable
internal fun CartScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onBackPressed: () -> Unit = {},
    totals: TotalModel,
    onProceedToCheckOut: () -> Unit = {},
    cartItemModels: List<CartItemModel> = emptyList(),
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
    onRemoveItemFromCart: (CartItemModel) -> Unit = {},
) {
    // Calculate subtotal based on cart item quantities and prices

    Scaffold(
        topBar = {
            ImaanTopAppBar(
                title = "Cart",
                onNavigationClick = onBackPressed
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        if (cartItemModels.isEmpty()) {
            EmptyCartView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            )
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(
                        top = it.calculateTopPadding(),
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
                        onRemoveItem = onRemoveItemFromCart,
                        onProceedToCheckOut = onProceedToCheckOut
                    )
                }
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
    onRemoveItem: (CartItemModel) -> Unit,
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
            key = { item -> item.productModel.id }
        ) {
            CartItemComponent(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                cartItemModel = it,
                onQuantityDecrease = onQuantityDecrease,
                onQuantityIncrease = onQuantityIncrease,
                onRemove = onRemoveItem
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
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                text = "Proceed to checkout",
                onClick = onProceedToCheckOut
            )
        }
    }
}

