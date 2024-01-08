package com.imaan.store.feature_cart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.store.R
import com.imaan.store.core.presentation.EmptyScreen
import com.imaan.store.feature_cart.domain.model.TotalModel
import com.imaan.store.feature_cart.domain.model.TotalType
import com.imaan.store.feature_cart.domain.model.dummyTotals
import com.imaan.store.feature_cart.presentation.composable.CartItem
import com.imaan.store.feature_cart.presentation.composable.CartItemModel
import com.imaan.store.feature_cart.presentation.composable.CartToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onBackPressed: () -> Unit = {},
    totals: List<TotalModel> = dummyTotals,
    onProceedToCheckOut: () -> Unit = {},
    cartItemModels: List<CartItemModel> = getDummyCartItems(),
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
) {
    // Calculate subtotal based on cart item quantities and prices
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = if (cartItemModels.isEmpty()) SheetValue.Hidden else SheetValue.Expanded,
            skipPartiallyExpanded = false
        )
    )
    val scope = rememberCoroutineScope()
    var sheetHeight by remember {
        mutableStateOf(320.dp)
    }
    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        sheetHeight = size.height.toDp() + 50.dp
                    }
                    .padding(horizontal = 24.dp)
                    .systemBarsPadding()
            ) {
                totals.forEach {
                    TotalItemComponent(modifier = Modifier.padding(vertical = 4.dp), total = it)
                }
                Spacer(modifier = Modifier.height(62.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { onProceedToCheckOut() }
                ) {
                    Text(
                        text = "Proceed to checkout"
                    )
                }
            }
        },
        topBar = {
            CartToolBar(
                onBackPressed = onBackPressed,
                onMorePressed = {
                    scope.launch {
                        sheetState.bottomSheetState.expand()
                    }
                }
            )
        },
        sheetShadowElevation = 120.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        sheetSwipeEnabled = false,
    ) {
        if (cartItemModels.isEmpty()) {
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                message = "Time to Explore and Fill it Up!",
                resId = R.drawable.ic_cart,
                title = "Empty Cart? Let's Start Shopping!"
            )
        }
        else {
            Column(
                modifier = Modifier
                    .padding(bottom = sheetHeight)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = it.calculateTopPadding()
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
                        onQuantityDecrease = onQuantityDecrease
                    )
                    Spacer(modifier = Modifier.height(sheetHeight))
                }
            }
        }
    }
}

@Composable
private fun TotalItemComponent(
    modifier: Modifier = Modifier,
    total: TotalModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = total.label
        )
        Text(
            text = total.amountString,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        )
    }
}

fun getDummyCartItems(number: Int = 5): List<CartItemModel> {
    val cartItemModels = mutableListOf<CartItemModel>()
    repeat(number) {
        cartItemModels.add(
            CartItemModel()
        )
    }
    return cartItemModels
}

@Composable
fun CartContent(
    items: List<CartItemModel>,
    modifier: Modifier = Modifier,
    onQuantityDecrease: (CartItemModel) -> Unit,
    onQuantityIncrease: (CartItemModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) {
            CartItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                cartItemModel = it,
                onQuantityDecrease = onQuantityDecrease,
                onQuantityIncrease = onQuantityIncrease
            )
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen()
}