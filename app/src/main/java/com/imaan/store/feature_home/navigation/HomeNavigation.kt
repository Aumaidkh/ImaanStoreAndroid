package com.imaan.store.feature_home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_FEATURE
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_ROUTE
import com.imaan.store.feature_home.presentation.home.HomeScreen

fun NavGraphBuilder.homeNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
){
    navigation(
        route = HOME_FEATURE,
        startDestination = HOME_ROUTE
    ){
        composable(
            route = HOME_ROUTE
        ){
            HomeScreen()
        }
    }
}