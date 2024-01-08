package com.imaan.store.feature_auth.presentation.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.google.common.truth.Truth.assertThat
import com.imaan.store.TestActivity
import com.imaan.store.core.domain.usecase.validation.ValidationException
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import com.imaan.store.feature_auth.navigation.AuthScreen
import com.imaan.store.feature_auth.presentation.utils.TestTags
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginButton
import com.imaan.store.feature_auth.presentation.utils.TestTags.phoneNumberField
import com.imaan.store.navigation.ImaanApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginScreenKtTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Inject
    lateinit var fakeRepository: IAuthRepository
    private lateinit var navController: TestNavHostController

    @Before
    fun setup(){
        hiltRule.inject()
    }
    @Test
    fun greeting_isVisible(){
       composeTestRule.setContent {
           LoginScreen()
       }
       composeTestRule.onNodeWithTag(TestTags.loginGreeting).assertIsDisplayed()
    }

    @Test
    fun pageSubtitle_isVisible() {
        composeTestRule.setContent {
            LoginScreen()
        }
        composeTestRule.onNodeWithTag(TestTags.subtitle).assertIsDisplayed()
    }

    @Test
    fun loginButton_isVisible() {
        composeTestRule.setContent {
            LoginScreen()
        }
        composeTestRule.onNodeWithTag(loginButton).assertIsDisplayed()
    }

    @Test
    fun phoneNumberField_isVisible() {
        composeTestRule.setContent {
            LoginScreen()
        }
        composeTestRule.onNodeWithTag(phoneNumberField).assertIsDisplayed()
    }

    @Test
    fun signUpButton_isVisible() {
        composeTestRule.apply{
            setContent {
                LoginScreen()
            }
            onNodeWithTag(TestTags.signUpText).assertIsDisplayed()
        }
    }

    @Test
    fun dontHaveAnAccount_isVisible() {
        composeTestRule.apply {
            setContent {
                LoginScreen()
            }
            onNodeWithTag(TestTags.dontHaveAccount).assertIsDisplayed()
        }
    }

    @Test
    fun loginClick_otpSent_takesUserToOtpScreen() {
        val event = mutableStateOf<UiEvent?>(null)
        val otp = OTP(value = 1234)

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            LoginScreen(
                event = event.value,
                onOtpSent = {
                    navController.navigate(
                        route = AuthScreen.VerifyOtp.route
                    )
                    assertThat(navController.currentBackStackEntry?.destination?.route).contains(
                        AuthScreen.VerifyOtp.route
                    )
                }
            )
        }
        event.value = UiEvent.Success<OTP?>(otp)

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun loginFailed_showsSnackbarWithMessage(): Unit = runBlocking {
        val loginError = "Error Logging in"
        composeTestRule.apply {
            val event = mutableStateOf<UiEvent?>(null)

            setContent {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }
                ) {
                    println("$it")
                    LoginScreen(
                        event = event.value,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
            event.value = UiEvent.Error(Exception(loginError))
            onNodeWithText(loginError)
                .assertIsDisplayed()
        }
    }

    @Test
    fun signUpClick_takesUserTo_signUpScreen() {
        composeTestRule.apply {
            setContent {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                ImaanApp(navController)
            }
            onNodeWithTag(TestTags.signUpText)
                .performClick()

        }
        val route = navController.currentBackStackEntry?.destination?.route
        assertThat(route).isEqualTo(AuthScreen.Register.route)
    }

    @Test
    fun loginClick_emptyPhoneNumber_showErrorOnPhoneNumberField() {
        composeTestRule.apply {
            setContent {
                val viewModel = hiltViewModel<LoginViewModel>()
                val uiState = viewModel.state.collectAsState().value
                LoginScreen(
                    onRequestOtp = viewModel::requestOtp,
                    state = uiState
                )

            }
            onNodeWithTag(loginButton)
                .performClick()

            onNodeWithText(ValidationException.EmptyPhoneNumberException.message.toString())
                .assertIsDisplayed()
        }
    }

    @Test
    fun loginClick_invalidPhoneNumber_showErrorOnPhoneNumberField() {
        composeTestRule.apply {
            setContent {
                val viewModel = hiltViewModel<LoginViewModel>()
                val uiState = viewModel.state.collectAsState().value
                LoginScreen(
                    state = uiState,
                    onRequestOtp = viewModel::requestOtp,
                    onPhoneNumberChange = viewModel::onPhoneNumberChange
                )

            }

            onNodeWithTag(phoneNumberField)
                .performTextInput("999990....")
            onNodeWithTag(loginButton)
                .performClick()
            onNodeWithText(ValidationException.InvalidPhoneNumberException.message.toString())
                .assertIsDisplayed()
        }
    }

}