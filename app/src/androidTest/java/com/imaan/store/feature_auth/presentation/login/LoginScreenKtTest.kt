package com.imaan.store.feature_auth.presentation.login

import androidx.compose.runtime.collectAsState
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
import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import com.imaan.store.feature_auth.navigation.AuthScreen
import com.imaan.store.feature_auth.navigation.NavigationConstants
import com.imaan.store.feature_auth.presentation.utils.TestTags
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginButton
import com.imaan.store.feature_auth.presentation.utils.TestTags.phoneNumberField
import com.imaan.store.navigation.ImaanApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
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
    lateinit var navController: TestNavHostController

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
    fun loginClick_loginSuccess_takesUserToOtpScreen() {
        val otp = (fakeRepository as? FakeAuthRepository)?.otp
        composeTestRule.apply {
            setContent {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                ImaanApp(navController = navController)
            }
            onNodeWithTag(phoneNumberField)
                .performTextInput("7889534384")
            onNodeWithTag(loginButton)
                .performClick()

        }
        val route = navController.currentBackStackEntry?.destination?.route
        assertThat(route).isEqualTo(NavigationConstants.VERIFY_OTP)

    }

    @Test
    fun loginFailed_showsSnackbarWithMessage() {
        val loginError = "Error Logging in"
        composeTestRule.apply {
            setContent {
                val repository = fakeRepository as? FakeAuthRepository
                repository?.error = Exception(loginError)
                val viewModel = hiltViewModel<LoginViewModel>()
                viewModel.onPhoneNumberChange("1234123222")
                val state = viewModel.state.collectAsState()
                LoginScreen(
                    state = state.value,
                    onRequestOtp = viewModel::requestOtp,
                )
            }
            onNodeWithTag(loginButton)
                .performClick()
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
                ImaanApp(navController = navController)
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
                val formState = viewModel.formState.collectAsState().value
                val uiState = viewModel.state.collectAsState().value
                LoginScreen(
                    formState = formState,
                    onRequestOtp = viewModel::requestOtp,
                    state = uiState
                )

            }
            onNodeWithTag(loginButton)
                .performClick()

            onNodeWithText("Phone Number can't be empty")
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
                .performTextInput("999990")
            onNodeWithTag(loginButton)
                .performClick()
            onNodeWithText("Phone Number should be of 10 digits")
                .assertIsDisplayed()

            onNodeWithTag(phoneNumberField)
                .performTextInput("999990aaaas")
            onNodeWithTag(loginButton)
                .performClick()
            onNodeWithText("Invalid Phone Number")
                .assertIsDisplayed()
        }
    }

}