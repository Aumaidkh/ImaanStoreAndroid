package com.imaan.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.categories.CategoryModel
import com.imaan.components.ProductCard
import com.imaan.components.ProductCardSize
import com.imaan.design_system.components.top_bars.ImaanAppTopBar
import com.imaan.design_system.components.top_bars.Type
import com.imaan.home.ui.components.CategoriesSection
import com.imaan.home.ui.components.ImaanCarousel
import com.imaan.home.ui.components.ImaanSearchBar
import com.imaan.products.ProductModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenUiState = HomeScreenUiState(),
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    onSeeAllCategoriesClick: () -> Unit = {},
    onAddToCart: (ProductModel) -> Unit = {},
    onCategoryClicked: (CategoryModel) -> Unit = {},
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        ImaanAppTopBar(
            user = state.user,
            modifier = Modifier
                .fillMaxWidth(),
            onNavigationClick = {
                onMenuClick()
            },
            onActionClick = onCartClick,
            cartItemsCount = if (state.cartItemCount == 0) null else state.cartItemCount,
            actionIconResId = com.imaan.resources.R.drawable.ic_cart,
            type = Type.WithProfilePic
        )
        HomeFeed(
            state = state,
            modifier = Modifier,
            onSeeAllCategoriesClick = onSeeAllCategoriesClick,
            onAddToCart = onAddToCart,
            onCategoryClick = onCategoryClicked
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFeed(
    modifier: Modifier = Modifier,
    state: HomeScreenUiState = HomeScreenUiState(),
    onSeeAllCategoriesClick: () -> Unit,
    onAddToCart: (ProductModel) -> Unit,
    onCategoryClick: (CategoryModel) -> Unit
) {
    val pagerState = rememberPagerState {
        state.offers.size
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top
        ) {
            item(
                span = { GridItemSpan(2) }
            ) {
                ImaanSearchBar()
            }
            item(
                span = { GridItemSpan(2) }
            ) {
                ImaanCarousel(
                    offers = state.offers,
                    pagerState = pagerState
                )
            }

            item(span = { GridItemSpan(2) }) {
                CategoriesSection(
                    onSeeAllCategoriesClick = onSeeAllCategoriesClick,
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = onCategoryClick
                )
            }

            items(items = state.products) {
                ProductCard(
                    modifier = Modifier
                        .padding(8.dp),
                    product = it,
                    onClick = {
                        // TODO: Navigate the user to the product description page
                    },
                    onAddToCart = onAddToCart,
                    size = ProductCardSize.Small
                )
            }
        }

        if (state.loading){
            CircularProgressIndicator()
        }
    }

}
