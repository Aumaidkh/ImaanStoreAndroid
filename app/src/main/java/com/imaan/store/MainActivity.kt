package com.imaan.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.imaan.store.navigation.ImaanApp
import com.imaan.store.design_system.ui.theme.ImaanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ImaanTheme {
                val navController = rememberNavController()
                ImaanApp(navController = navController)
            }
        }
    }
}
