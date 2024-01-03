package com.imaan.store.feature_auth.presentation.register

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.espresso.Espresso
import com.imaan.store.TestActivity
import com.imaan.store.core.utils.buttonIsDisabled
import com.imaan.store.core.utils.buttonIsEnabled
import com.imaan.store.core.utils.elementWithTagIsDisplayed
import com.imaan.store.core.utils.inputTextIn
import com.imaan.store.feature_auth.presentation.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class RegisterScreenKtTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<TestActivity>()


    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun content_isDisplayed() {
        composeRule.apply{
            setContent {
                RegisterScreen()
            }

            elementWithTagIsDisplayed(TestTags.registerGreeting)
            elementWithTagIsDisplayed(TestTags.registerSubtitle)
            elementWithTagIsDisplayed(TestTags.phoneNumberField)
            elementWithTagIsDisplayed(TestTags.fullNameField)
            elementWithTagIsDisplayed(TestTags.registerButton)
            elementWithTagIsDisplayed(TestTags.loginButton)
            elementWithTagIsDisplayed(TestTags.alreadyHaveAccount)
            elementWithTagIsDisplayed(TestTags.termsOfUse)
        }

    }

    @Test
    fun registerButton_state_updatesCorrectly() {
        composeRule.apply{
            setContent {
                val viewModel = hiltViewModel<ScreenViewModel>()
                val uiState = viewModel.state.collectAsState().value
                RegisterScreen(
                    state = uiState,
                    onFullNameChange = viewModel::onFullNameChange,
                    onPhoneChange = viewModel::onPhoneNumberChange
                )
            }

            // Button is disabled
            buttonIsDisabled(TestTags.registerButton)

            inputTextIn(TestTags.fullNameField,"Hello")
            buttonIsDisabled(TestTags.registerButton)

            inputTextIn(TestTags.phoneNumberField,"1221212")
            Espresso.closeSoftKeyboard()
            buttonIsEnabled(TestTags.registerButton)


        }
    }

}