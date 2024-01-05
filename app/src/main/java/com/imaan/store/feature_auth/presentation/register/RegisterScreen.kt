package com.imaan.store.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.store.R
import com.imaan.store.design_system.composables.BigHeading
import com.imaan.store.design_system.composables.ImaanInputField
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.design_system.composables.SmallHeading
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_auth.presentation.composables.AuthIllustration
import com.imaan.store.feature_auth.presentation.login.deviceDensity
import com.imaan.store.feature_auth.presentation.utils.TestTags

@Composable
fun RegisterScreen(
    state: RegisterScreenUiState = RegisterScreenUiState(),
    event: UiEvent? = null,
    onFullNameChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onRegister: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onNavigateToVerifyOtpScreen: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues()
) {

    LaunchedEffect(key1 = event){
        when(event){
            is UiEvent.Error -> {
                snackbarHostState.showSnackbar(
                    message = event.errorMessage
                )
            }
            is UiEvent.Success<*> -> {
                onNavigateToVerifyOtpScreen()
            }
            null -> Unit
        }
    }

    Column(
        modifier = Modifier
            .semantics {
                testTag = TestTags.registerScreen
            }
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        AuthIllustration(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            imageResId = R.drawable.masjid
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BigHeading(
                text = stringResource(id = R.string.register),
                tag = TestTags.registerGreeting
            )
            SmallHeading(
                text = stringResource(id = R.string.create_your_account),
                tag = TestTags.registerSubtitle
            )

            RegisterForm(
                modifier = Modifier
                    .fillMaxWidth(),
                state = state,
                onFullNameChange = onFullNameChange,
                onPhoneChange = onPhoneChange,
                onRegister = onRegister,
                onLoginClick = onLoginClick
            )
        }
    }
}

@Composable
private fun RegisterForm(
    modifier: Modifier = Modifier,
    state: RegisterScreenUiState = RegisterScreenUiState(),
    onFullNameChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onRegister: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Full Name field
        ImaanInputField(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.fullNameField
                }
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 24.dp, end = 24.dp),
            value = state.fullName.value,
            error = state.fullName.error,
            onValueChange = onFullNameChange,
            iconResId = R.drawable.ic_person,
            label = stringResource(id = R.string.full_name),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

        ImaanInputField(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.phoneNumberField
                }
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 24.dp, end = 24.dp),
            value = state.phoneNumber.value,
            error = state.phoneNumber.error,
            onValueChange = onPhoneChange,
            iconResId = R.drawable.ic_phone,
            label = stringResource(id = R.string.phone),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            maxLength = 10
        )

        LoadingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
                .testTag(TestTags.registerButton),
            text = stringResource(id = R.string.register),
            onClick = onRegister,
            loading = state.loading,
            enabled = state.buttonEnabled
        )

        Text(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.termsOfUse
                }
                .padding(32.dp)
                .fillMaxWidth(0.80f),
            text = buildAnnotatedString {
                append(stringResource(id = R.string.by_registering))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                ){
                    append(stringResource(id = R.string.terms_of_use))
                    append(" ")
                }
                append(stringResource(id = R.string.and))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                ){
                    append(stringResource(id = R.string.privacy_notice))
                }
            },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )
        Spacer(
            modifier = Modifier
                .weight(0.7f)
        )
        Text(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.alreadyHaveAccount
                },
            text = stringResource(R.string.already_have_account),
        )


        TextButton(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.loginText
                }
                .windowInsetsBottomHeight(WindowInsets.systemBars),
            onClick = {
                onLoginClick()
            },
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 17.sp
                )
            )
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))


    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

@Preview
@Composable
fun RegisterFormPreview() {
    RegisterForm()
}
