package com.imaan.store.feature_home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_cart.navigation.CartScreen
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_FEATURE
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_ROUTE
import com.imaan.store.feature_home.presentation.categories.CategoriesScreen
import com.imaan.store.feature_home.presentation.home.HomeScreenViewModel


fun NavGraphBuilder.homeNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {


    navigation(
        route = HOME_FEATURE,
        startDestination = HOME_ROUTE
    ) {
        composable(
            route = HOME_ROUTE
        ) {

            val viewModel = hiltViewModel<HomeScreenViewModel>()
            val state = viewModel.state.collectAsState().value
            val event = viewModel.event.collectAsState(null).value
            when (event) {
                is UiEvent.Success<*> -> {
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
                onCartClick = {
                    navController.navigate(
                        route = CartScreen
                    )
                },
                onSeeAllCategoriesClick = {
                    navController.navigate(
                        route = Routes.Categories.route
                    )
                },
                onAddToCart = viewModel::onAddToCart,
                onCategoryClicked = viewModel::onSelectCategory
            )
        }

        composable(
            route = Routes.Categories.route
        ) {
            CategoriesScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}