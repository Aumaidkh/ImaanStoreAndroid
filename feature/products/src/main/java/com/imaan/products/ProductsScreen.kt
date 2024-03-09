package com.imaan.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.design_system.components.views.ImaanAppDropDownMenu
import com.imaan.design_system.components.views.ProductCard
import com.imaan.design_system.components.views.ProductCardSize



@Composable
fun ProductsScreen(
    uiState: ProductsScreenUiState = ProductsScreenUiState(),
    paddingValues: PaddingValues = PaddingValues(),
    onProductClick: (ProductModel) -> Unit,
    onAddProductToCartClick: (ProductModel) -> Unit
) {
    var filterExpanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 30.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = uiState.query,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            ImaanAppDropDownMenu(
                label = "Filter",
                iconResId = com.imaan.design_system.R.drawable.filter,
                iconTint = MaterialTheme.colorScheme.primary,
                onClick = {
                    filterExpanded = !filterExpanded
                },
                onMenuItemClick = {
                    filterExpanded = false
                },
                expanded = filterExpanded,
                onDismissRequest = {
                    filterExpanded = false
                },
                dropDownMenuItems = Filter.values().toList()
            )
        }
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    columns = GridCells.Fixed(2)
                ) {
                    items(
                        items = uiState.products,
                        key = { it.id.value }
                    ) {
                        ProductCard(
                            modifier = Modifier
                                .padding(2.dp),
                            product = it,
                            onClick = onProductClick,
                            onAddToCart = onAddProductToCartClick,
                            size = ProductCardSize.Small
                        )
                    }
                }
    }

}
