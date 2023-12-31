package com.imaan.store.feature_auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.imaan.store.R
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.presentation.utils.TestTags.dontHaveAccount
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginButton
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginGreeting
import com.imaan.store.feature_auth.presentation.utils.TestTags.phoneNumberField
import com.imaan.store.feature_auth.presentation.utils.TestTags.signUpText
import com.imaan.store.feature_auth.presentation.utils.TestTags.subtitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginUiState = LoginUiState.Initial(),
    phone: String = "",
    onPhoneNumberChange: (String) -> Unit = {},
    onRequestOtp: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onOtpSent: (OTP) -> Unit = {}
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = state.error) {
        if (state is LoginUiState.PhoneError) {
            return@LaunchedEffect
        }
        state.error?.let {
            snackbarHostState.showSnackbar(
                message = it.message.toString()
            )
        }
    }
    when(state){
        is LoginUiState.Loading -> {}
        is LoginUiState.OtpSent -> {
            onOtpSent(state.otp)
        }
        else -> {

        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .semantics {
                        testTag = loginGreeting
                    },
                text = stringResource(R.string.login_greeting),
            )

            Text(
                modifier = Modifier
                    .semantics {
                        testTag = subtitle
                    },
                text = stringResource(R.string.login_page_subtitle),
            )

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .semantics {
                        testTag = phoneNumberField
                    },
                value = phone,
                onValueChange = onPhoneNumberChange
            )
            if (state is LoginUiState.PhoneError) {
                Text(
                    text = state.errorMessage,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.error
                    )
                )
            }

            Button(
                modifier = Modifier
                    .semantics {
                        testTag = loginButton
                    },
                onClick = { onRequestOtp() }
            ) {
                Text(
                    text = stringResource(id = R.string.login)
                )
            }

            Text(
                modifier = Modifier
                    .semantics {
                        testTag = dontHaveAccount
                    },
                text = stringResource(R.string.login_page_dont_have_account),
            )


            Text(
                modifier = Modifier
                    .semantics {
                        testTag = signUpText
                    }
                    .clickable {
                        onSignUpClick()
                    },
                text = stringResource(R.string.login_signup),
            )
        }
    }

}



