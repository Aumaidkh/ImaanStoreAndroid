package com.imaan.store.feature_home.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.imaan.store.core.domain.model.ProductModel
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.feature_home.domain.models.CategoryModel
import com.imaan.store.feature_home.presentation.composables.CategoryItem
import com.imaan.store.feature_home.presentation.composables.ImaanTopAppBar
import com.imaan.store.feature_home.presentation.composables.OfferCard
import com.imaan.store.feature_home.presentation.composables.OfferModel
import com.imaan.store.feature_home.presentation.composables.ProductCardSmall
import com.imaan.store.feature_home.presentation.composables.Size
import com.imaan.store.feature_home.presentation.composables.getDummyOffer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    Scaffold(
        modifier = modifier,
        topBar = {
            ImaanTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                onMenuClick = onMenuClick,
                onCartClick = onCartClick,
                cartItemsCount = if (state.cartItemCount == 0) null else state.cartItemCount
            )
        }
    ) {
        HomeFeed(
            state = state,
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                ),
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
                AwesomeCarousel(
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
                val imageRequest = ImageRequest
                    .Builder(LocalContext.current)
                    .data(it.imageUrl)
                    .build()
                ProductCardSmall(
                    modifier = Modifier
                        .padding(8.dp),
                    product = it,
                    imageRequest = imageRequest,
                    onClick = {
                        // TODO: Navigate the user to the product description page
                    },
                    onAddToCart = onAddToCart
                )
            }
        }

        if (state.loading){
            CircularProgressIndicator()
        }
    }


}

@Composable
fun CategoriesSection(
    onSeeAllCategoriesClick: () -> Unit,
    categories: List<CategoryModel>,
    selectedCategory: CategoryModel?,
    onCategorySelected:(CategoryModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 19.sp
                )
            )
            TextButton(onClick = { onSeeAllCategoriesClick() }) {
                Text(text = "See all")
            }
        }
        LazyRow {
            items(
                items = categories
            ) {
                CategoryItem(
                    modifier = Modifier
                        .padding(8.dp),
                    categoryModel = it,
                    onClick = {onCategorySelected(it)},
                    size = Size.SMALL,
                    isSelected = selectedCategory == it
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AwesomeCarousel(
    offers: List<OfferModel> = getDummyOffer(10),
    pageCount: Int = 10,
    pagerState: PagerState,
    autoScrollDuration: Long = 3000L
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(
        modifier = Modifier
            .padding(top = 24.dp),
        state = pagerState,
        pageSpacing = 18.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(4.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fixed(320.dp),
        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = {
            OfferCard(
                offer = offers[it]
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanSearchBar() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 0.dp),
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        shape = MaterialTheme.shapes.medium,
        placeholder = {
            Text(text = "Search")
        }
    ) {

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    ImaanTheme {
        HomeScreen()
    }
}