package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.products.ProductModel
import com.imaan.wishlist.WishlistScreen
import com.imaan.wishlist.WishlistScreenViewModel

fun NavGraphBuilder.wishlistNavigationProvider(
    onProductClick:(ProductModel) -> Unit,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
){
    composable(
        route = WishlistRoute.route
    ){
        val viewModel = hiltViewModel<WishlistScreenViewModel>()
        val uiState = viewModel.state.collectAsState().value

        WishlistScreen(
            uiState = uiState,
            paddingValues = paddingValues
        )
    }
}