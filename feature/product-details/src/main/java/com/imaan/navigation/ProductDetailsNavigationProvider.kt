package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.product_details.ProductDetailsScreen
import com.imaan.product_details.ProductDetailsScreenViewModel
import com.imaan.products.ProductModel

fun NavGraphBuilder.productDetailsNavigationProvider(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onRecommendedItemClick: (ProductModel) -> Unit,
    onCartClick:() -> Unit,
    onBuyNow: () -> Unit
) {
    composable(
        route = ProductDetailsRoute.route
    ) {
        val viewModel = hiltViewModel<ProductDetailsScreenViewModel>()
        val uiState = viewModel.state.collectAsState().value

        LaunchedEffect(key1 = uiState.buyProductNow){
            if (uiState.buyProductNow){
                onBuyNow()
                viewModel.onBuyNowHandled()
            }
        }

        ProductDetailsScreen(
            paddingValues = paddingValues,
            uiState = uiState,
            onSizeSelected = viewModel::selectSize,
            onColorSelected = viewModel::selectColor,
            onVariantSelected = viewModel::selectCustom,
            onSelectVariant = viewModel::selectVariant,
            onAddToCart = {
                onCartClick()
            },
            onBuyNow = viewModel::buyNow,
            onAddToFavorites = { product, isFavorite ->
                // TODO: Add to favorites
            },
            onRecommendedItemClick = {
                // TODO:
            }
        )
    }
}