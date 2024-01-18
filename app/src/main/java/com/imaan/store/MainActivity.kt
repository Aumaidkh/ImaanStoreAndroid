package com.imaan.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.imaan.home.navigation.HomeRoute
import com.imaan.store.navigation.ImaanApp
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.feature_home.navigation.NavigationConstants.HOME_FEATURE
import com.imaan.store.feature_home.navigation.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ImaanTheme {
                val navController = rememberNavController()
                ImaanApp(navController,HomeRoute.route, rememberCoroutineScope())
            }
        }
    }
}
