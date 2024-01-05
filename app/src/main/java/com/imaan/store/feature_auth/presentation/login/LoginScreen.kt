package com.imaan.store.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.store.R
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.design_system.composables.BigHeading
import com.imaan.store.design_system.composables.ImaanInputField
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.design_system.composables.SmallHeading
import com.imaan.store.feature_auth.domain.model.OTP
import com.imaan.store.feature_auth.presentation.composables.AuthIllustration
import com.imaan.store.feature_auth.presentation.utils.TestTags.dontHaveAccount
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginButton
import com.imaan.store.feature_auth.presentation.utils.TestTags.loginGreeting
import com.imaan.store.feature_auth.presentation.utils.TestTags.phoneNumberField
import com.imaan.store.feature_auth.presentation.utils.TestTags.signUpText
import com.imaan.store.feature_auth.presentation.utils.TestTags.subtitle

private const val TAG = "LoginScreen"

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    state: LoginScreenUiState = LoginScreenUiState(),
    event: UiEvent? = null,
    onPhoneNumberChange: (String) -> Unit = {},
    onRequestOtp: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onOtpSent: (OTP) -> Unit = {},
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
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
                val otp = event.data as? OTP
                otp?.let { onOtpSent(it) }
            }

            null -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AuthIllustration(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            imageResId = R.drawable.masjid
        )

        BigHeading(
            text = stringResource(id = R.string.login_greeting),
            tag = loginGreeting
        )

        SmallHeading(
            text = stringResource(id = R.string.login_page_subtitle),
            tag = subtitle
        )

        ImaanInputField(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .semantics {
                    testTag = phoneNumberField
                },
            value = state.phone.value,
            onValueChange = onPhoneNumberChange,
            maxLength = 10,
            error = state.phone.error,
            iconResId = R.drawable.ic_phone,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )

        LoadingButton(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(24.dp)
                .fillMaxWidth()
                .height(50.dp)
                .semantics {
                    testTag = loginButton
                },
            onClick = { onRequestOtp() },
            loading = state.loading,
            text = stringResource(id = R.string.request_otp)
        )

        Spacer(modifier = Modifier
            .weight(0.8f)
            .background(color = Color.Red))

        Text(
            modifier = Modifier
                .semantics {
                    testTag = dontHaveAccount
                },
            text = stringResource(R.string.login_page_dont_have_account),
        )


        TextButton(
            modifier = Modifier
                .semantics {
                    testTag = signUpText
                }
                .windowInsetsBottomHeight(WindowInsets.systemBars),
            onClick = {
                onSignUpClick()
            },
        ) {
            Text(
                text = stringResource(id = R.string.login_signup),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 17.sp
                )
            )
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }

}

enum class Density{
    LowDensity,
    HighDensity
}

@Composable
fun deviceDensity(): Density{
    val density = LocalDensity.current.density
    if (density.dp <= 2.4.dp){
        return Density.HighDensity
    }
    return Density.LowDensity
}
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}



