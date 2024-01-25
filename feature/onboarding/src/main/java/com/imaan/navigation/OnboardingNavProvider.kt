package com.imaan.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imaan.onboarding.OnBoardingPagesProviderImpl
import com.imaan.onboarding.OnBoardingScreen

fun NavGraphBuilder.onBoardingNavigationProvider(
    paddingValues: PaddingValues = PaddingValues(),
    onNavigateToLogin: () -> Unit = {},
) {
    composable(
        route = OnboardingRoute.route
    ){
        val scope = rememberCoroutineScope()
        OnBoardingScreen(
            paddingValues = paddingValues,
            onNextClick = onNavigateToLogin,
            onBoardingPagesProvider = OnBoardingPagesProviderImpl(),
            scope = scope
        )
    }
}