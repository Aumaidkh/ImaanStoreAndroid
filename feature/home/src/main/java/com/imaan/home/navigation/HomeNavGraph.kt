package com.imaan.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.imaan.home.ui.HomeEvent
import com.imaan.home.ui.HomeScreenViewModel
import com.imaan.home.ui.components.HomeScreenDrawer


fun NavGraphBuilder.homeNavigationProvider(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onNavigateToCart: () -> Unit,
    onNavigateToCategories: () -> Unit,
    paddingValues: PaddingValues
) {

    composable(
        route = HomeRoute.route
    ) {

        val viewModel = hiltViewModel<HomeScreenViewModel>()
        val state = viewModel.state.collectAsState().value
        val event = viewModel.event.collectAsState(null).value
        when (event) {
            is HomeEvent.Success<*> -> {
                LaunchedEffect(Unit){
                    snackbarHostState.showSnackbar(
                        event.data as String
                    )
                }
            }

            else -> {}
        }
        HomeScreenDrawer(
            state = state,
            paddingValues = paddingValues,
            onCartClick = onNavigateToCart,
            onSeeAllCategoriesClick = onNavigateToCategories,
            onAddToCart = viewModel::onAddToCart,
            onCategoryClicked = viewModel::onSelectCategory,
            onNavigateToProfileScreen = {}
        )
    }
}