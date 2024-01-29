package com.imaan.navigation

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.cart.CartScreen
import com.imaan.cart.CartScreenEvent
import com.imaan.cart.CartViewModel

fun NavGraphBuilder.cartNavigationProvider(
    paddingValues: PaddingValues,
    onNavigateToAddresses: () -> Unit,
    onContinueShopping: () -> Unit,
    onError: (Throwable) -> Unit
) {
    composable(
        route = CartRoute.route,
        enterTransition = {makeCartAppearFromBottom()},
        exitTransition = { makeCartDisappearFromBottom()},
        popExitTransition = { makeCartDisappearFromBottom()},
        popEnterTransition = { makeCartAppearFromBottom()}
    ) {
        val viewModel = hiltViewModel<CartViewModel>()
        val state = viewModel.state.collectAsState()
        val event = viewModel.eventFlow.collectAsState(null).value

        LaunchedEffect(key1 = event){
            when(event){
                is CartScreenEvent.Success<*> -> {
                    onNavigateToAddresses()
                }
                is CartScreenEvent.Error -> {
                    onError(event.throwable)
                }

                else -> {}
            }
        }

        CartScreen(
            paddingValues = paddingValues,
            totals = state.value.totalModel,
            cartItemModels = state.value.items,
            onQuantityDecrease = viewModel::decreaseQuantity,
            onQuantityIncrease = viewModel::increaseQuantity,
            onProceedToCheckOut = viewModel::proceedToCheckOut,
            onRemoveItemFromCart = viewModel::removeItemFromCart,
            onContinueShoppingClick = onContinueShopping
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