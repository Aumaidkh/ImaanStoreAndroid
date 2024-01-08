package com.imaan.store.feature_cart.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.imaan.store.feature_cart.domain.model.TotalModel
import com.imaan.store.feature_cart.domain.model.TotalType
import com.imaan.store.feature_cart.presentation.CartScreen
import com.imaan.store.feature_cart.presentation.CartViewModel
import com.imaan.store.feature_cart.presentation.composable.CartItemModel

fun NavGraphBuilder.cartNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    composable(
        route = CartScreen,
        enterTransition = {makeCartAppearFromBottom()},
        exitTransition = { makeCartDisappearFromBottom()},
        popExitTransition = { makeCartDisappearFromBottom()},
        popEnterTransition = { makeCartAppearFromBottom()}
    ) {
        val viewModel = hiltViewModel<CartViewModel>()
        val state = viewModel.state.collectAsState()
        CartScreen(
            paddingValues = paddingValues,
            onBackPressed = {
                navController.popBackStack()
            },
            totals = state.value.totals,
            cartItemModels = state.value.items,
            onQuantityDecrease = viewModel::decreaseQuantity,
            onQuantityIncrease = viewModel::increaseQuantity
        )
    }
}

fun makeCartAppearFromBottom(): EnterTransition {
    return fadeIn() + slideInVertically(
        animationSpec = tween(
            durationMillis = 400,
            easing = EaseIn
        ),
        initialOffsetY = {
            2 * it
        },
    )
}

fun makeCartDisappearFromBottom(): ExitTransition {
    return fadeOut() + slideOutVertically(
        animationSpec = tween(
            durationMillis = 400,
            easing = EaseIn
        ),
        targetOffsetY = {
            2*it
        }
    )
}