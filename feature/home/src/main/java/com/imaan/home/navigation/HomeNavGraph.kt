package com.imaan.home.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.categories.CategoryModel
import com.imaan.design_system.components.dialogs.ImaanActionDialog
import com.imaan.home.ui.HomeEvent
import com.imaan.home.ui.HomeScreenViewModel
import com.imaan.home.ui.components.HomeScreenDrawer


fun NavGraphBuilder.homeNavigationProvider(
    snackbarHostState: SnackbarHostState,
    onNavigateToCart: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onSignOut: () -> Unit,
    paddingValues: PaddingValues,
    onCategoryClicked: (CategoryModel?) -> Unit,
    onWishlistClicked: () -> Unit
) {

    composable(
        route = HomeRoute.route
    ) {
        var showSignOutConfirmation by remember {
            mutableStateOf(false)
        }

        val viewModel = hiltViewModel<HomeScreenViewModel>()
        val state = viewModel.state.collectAsState().value
        val event = viewModel.event.collectAsState(null).value
        when (event) {
            is HomeEvent.Success<*> -> {
                LaunchedEffect(Unit) {
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
            onCategoryClicked = {
                onCategoryClicked(it)
                viewModel.onSelectCategory(it)
            },
            onNavigateToProfileScreen = onNavigateToProfile,
            onSignOutClick = {
                showSignOutConfirmation = true
            },
            onWishlistClick = onWishlistClicked
        )

        AnimatedVisibility(visible = showSignOutConfirmation) {
            ImaanActionDialog(
                title = "Sign Out",
                body = "Are you sure you want to sign out?",
                positiveButtonText = "Sign me out",
                negativeButtonText = "Discard",
                onDismissRequest = {
                    showSignOutConfirmation = false
                },
                onPositiveButtonClick = {
                    showSignOutConfirmation = false
                    onSignOut()
                },
                onNegativeButtonClick = {
                    showSignOutConfirmation = false
                }
            )
        }
    }
}