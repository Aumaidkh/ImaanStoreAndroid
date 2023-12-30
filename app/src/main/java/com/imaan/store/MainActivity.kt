package com.imaan.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.imaan.store.feature_auth.presentation.login.LoginScreen
import com.imaan.store.feature_auth.presentation.login.LoginViewModel
import com.imaan.store.ui.theme.ImaanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ImaanTheme {
//                val viewModel = hiltViewModel<LoginViewModel>()
//                val state = viewModel.state.collectAsState()
//                LoginScreen(
//                    state = state.value,
//                    onLogin = viewModel::login
//                )
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImaanTheme {
        Greeting("Android")
    }
}