package com.imaan.store.feature_auth.presentation.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.imaan.store.feature_auth.presentation.utils.TestTags
import com.imaan.store.feature_auth.presentation.utils.TestTags.signUpPage
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()
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
    fun loginClick_loginSuccess_takesUserToHomeScreen() {
        composeTestRule.apply {
            setContent {
                LoginScreen()
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
                LoginScreen(
                    errorMessage = loginError
                )
            }

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