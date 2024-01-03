package com.imaan.store.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onNavigateToVerifyOtpScreen: () -> Unit = {}
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
    ) {
        AuthIllustration(
            density = deviceDensity(),
            imageResId = R.drawable.masjid
        )

        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BigHeading(
                text = stringResource(id = R.string.register),
                tag = TestTags.loginGreeting
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
fun RegisterForm(
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
                .padding(start = 24.dp, end = 24.dp),
            text = stringResource(id = R.string.register),
            onClick = onRegister,
            loading = state.loading,
            enabled = state.buttonEnabled
        )

        Text(
            modifier = Modifier
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
                .weight(1f)
        )
        Text(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.alreadyHaveAccount
                },
            text = stringResource(R.string.already_have_account),
        )


        Text(
            modifier = Modifier
                .padding(24.dp)
                .semantics {
                    testTag = TestTags.signUpText
                }
                .clickable {
                    onLoginClick()
                },
            text = stringResource(R.string.login),
            style = TextStyle(
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Medium
            )
        )

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
