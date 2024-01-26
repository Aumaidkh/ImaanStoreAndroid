package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.products.ProductModel
import com.imaan.products.ProductsScreen
import com.imaan.products.ProductsScreenViewModel

fun NavGraphBuilder.productsNavigationProvider(
    paddingValues: PaddingValues,
    onNavigateToProductDetails: (ProductModel) -> Unit,
    snackbarHostState: SnackbarHostState
){
    composable(
        route = ProductsRoute.route
    ){
        val viewModel = hiltViewModel<ProductsScreenViewModel>()
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(key1 = state.infoMessage){
            state.infoMessage?.let {
                snackbarHostState.showSnackbar(it).also { snackBarResult ->
                    when(snackBarResult){
                        SnackbarResult.Dismissed -> {
                            viewModel.onInfoMessageHandled()
                        }
                        else -> Unit
                    }
                }
            }

        }
        ProductsScreen(
            uiState = state,
            paddingValues = paddingValues,
            onProductClick = onNavigateToProductDetails,
            onAddProductToCartClick = viewModel::addProductToCart
        )
    }
}