package com.imaan.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface Feature {
    val navController: NavHostController
    val entry: String
    val content: @Composable () -> Unit
}