package com.imaan.store.feature_cart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.store.R
import com.imaan.store.core.domain.model.Amount
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.core.presentation.EmptyScreen
import com.imaan.store.design_system.composables.CustomToolBar
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.design_system.ui.theme.poppinsFontFamily
import com.imaan.store.feature_cart.domain.model.TotalModel
import com.imaan.store.feature_cart.presentation.composable.CartItemComponent
import com.imaan.store.feature_cart.presentation.composable.CartItemModel

@Composable
fun CartScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onBackPressed: () -> Unit = {},
    totals: TotalModel,
    onProceedToCheckOut: () -> Unit = {},
    cartItemModels: List<CartItemModel> = getDummyCartItems(),
    onQuantityIncrease: (CartItemModel) -> Unit = {},
    onQuantityDecrease: (CartItemModel) -> Unit = {},
    onRemoveItemFromCart: (CartItemModel) -> Unit = {},
) {
    // Calculate subtotal based on cart item quantities and prices

    Scaffold(
        topBar = {
            CustomToolBar(
                title = "Cart",
                onBackPressed = onBackPressed
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
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
private fun TotalItemComponent(
    modifier: Modifier = Modifier,
    total: Amount,
    label: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            ),
        )
        Text(
            text = total.inRupees,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

val dummyCart = mutableListOf<CartItemModel>()

fun addDuumyToCart(item: ProductModel): Boolean {
    val cartItem =
        dummyCart.find { it.productModel == item }

    if (cartItem == null){
        dummyCart.add(
            CartItemModel(
                productModel = item,
                quantity = 1
            )
        )
        return true
    }
    val itemIndex = dummyCart.indexOf(cartItem)
    if (itemIndex != -1){
        dummyCart[itemIndex] = cartItem.copy(
            quantity = cartItem.quantity ++
        )
        return true
    }
    return false
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

@Composable
private fun TotalComponent(totals: TotalModel,
                           paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        PromoComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        )
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.subtotal, label = "Subtotal")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.deliveryCharges, label = "Delivery charges")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.discount, label = "Discount")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.grandTotal, label = "Grand Total")
        Divider(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
                .fillMaxWidth(),
            thickness = 0.7.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.3f
            )
        )
    }
}

@Composable
fun PromoComponent(
    modifier: Modifier = Modifier,
    promoValid: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        trailingIcon = {

        },
        placeholder = {
            Text(text = "APPLY PROMO")
        },
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.4f
            )
        )
    )
}
