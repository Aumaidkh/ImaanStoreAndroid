package com.imaan.store.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.imaan.store.feature_auth.navigation.NavigationConstants
import com.imaan.store.feature_auth.navigation.authNavigation
import com.imaan.store.feature_home.navigation.homeNavigation

private const val TAG = "ImaanApp"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanApp(
    navController: NavHostController
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    var showBottomBar by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        bottomBar = {
            if (showBottomBar){
                BottomAppBar {
                    for (i in 0 until 4){
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationConstants.AUTH_FEATURE
        ) {
            authNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )

            homeNavigation(
                navController = navController,
                snackbarHostState = snackbarHostState,
                paddingValues = it
            )
        }
    }
}