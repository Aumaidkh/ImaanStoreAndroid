package com.imaan.store.feature_auth.presentation.login

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import com.imaan.store.TestActivity
import com.imaan.store.feature_auth.domain.repository.FakeAuthRepository
import com.imaan.store.feature_auth.domain.repository.IAuthRepository
import com.imaan.store.feature_auth.presentation.utils.TestTags
import com.imaan.store.feature_auth.presentation.utils.TestTags.signUpPage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
        composeTestRule.onNodeWithTag(TestTags.loginButton).assertIsDisplayed()
    }

    @Test
    fun phoneNumberField_isVisible() {
        composeTestRule.setContent {
            LoginScreen()
        }
        composeTestRule.onNodeWithTag(TestTags.phoneNumberField).assertIsDisplayed()
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
        composeTestRule.apply {
            setContent {

            }
            onNodeWithTag(TestTags.loginButton)
                .performClick()

            onNodeWithTag(signUpPage)
                .assertIsDisplayed()
        }
    }

    @Test
    fun loginFailed_showsSnackbarWithMessage() {
        val loginError = "Error Logging in"
        composeTestRule.apply {
            setContent {
                val repository = fakeRepository as? FakeAuthRepository
                repository?.error = Exception(loginError)
                val viewModel = hiltViewModel<LoginViewModel>()
                viewModel.onPhoneNumberChange("12341232")
                val state = viewModel.state.collectAsState()
                LoginScreen(
                    state = state.value,
                    onLogin = viewModel::login,
                )
            }
            onNodeWithTag(TestTags.loginButton)
                .performClick()
            onNodeWithText(loginError)
                .assertIsDisplayed()
        }
    }

    @Test
    fun signUpClick_takesUserTo_signUpScreen() {
        composeTestRule.apply {
            setContent {
                LoginScreen()
            }
            onNodeWithTag(TestTags.signUpText)
                .performClick()

            onNodeWithTag(signUpPage)
                .assertIsDisplayed()
        }
    }
}