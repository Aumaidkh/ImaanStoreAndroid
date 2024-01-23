package com.imaan.home.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.design_system.components.dialogs.ImaanActionDialog
import com.imaan.home.ui.HomeEvent
import com.imaan.home.ui.HomeScreenViewModel
import com.imaan.home.ui.components.HomeScreenDrawer
import com.imaan.util.DrawerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun NavGraphBuilder.homeNavigationProvider(
    snackbarHostState: SnackbarHostState,
    onNavigateToCart: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onSignOut: () -> Unit,
    paddingValues: PaddingValues,
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
            onCategoryClicked = viewModel::onSelectCategory,
            onNavigateToProfileScreen = onNavigateToProfile,
            onSignOutClick = {
                showSignOutConfirmation = true
            },
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