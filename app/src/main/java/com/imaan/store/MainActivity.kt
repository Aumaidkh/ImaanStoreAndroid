package com.imaan.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.imaan.auth.IAuthService
import com.imaan.home.navigation.HomeRoute
import com.imaan.navigation.AuthRoute
import com.imaan.navigation.OnboardingRoute
import com.imaan.navigation.Orders
import com.imaan.store.design_system.ui.theme.ImaanTheme
import com.imaan.store.navigation.ImaanApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var iAuthService: IAuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        this.enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ImaanTheme {
                val destination = if (iAuthService.currentUser == null) AuthRoute.Feature else HomeRoute.route
                val navController = rememberNavController()
                ImaanApp(
                    navController,
                    destination,
                    rememberCoroutineScope()
                )
            }
        }
    }
}
