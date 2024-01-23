package com.imaan.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.auth.R
import com.imaan.auth.components.AuthIllustration
import com.imaan.auth.components.BigHeading
import com.imaan.auth.components.SmallHeading
import com.imaan.auth.utils.TestTags
import com.imaan.auth.utils.TestTags.dontHaveAccount
import com.imaan.auth.utils.TestTags.loginButton
import com.imaan.auth.utils.TestTags.loginGreeting
import com.imaan.auth.utils.TestTags.phoneNumberField
import com.imaan.auth.utils.TestTags.signUpText
import com.imaan.auth.utils.TestTags.subtitle
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.design_system.components.input_fields.ImaanInputFieldWithIcon

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    state: LoginScreenUiState = LoginScreenUiState(),
    onPhoneNumberChange: (String) -> Unit = {},
    onRequestOtp: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    focusManager: FocusManager = LocalFocusManager.current
) {

    Column(
        modifier = Modifier
            .testTag(TestTags.loginScreen)
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AuthIllustration(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp),
            imageResId = com.imaan.design_system.R.drawable.masjid
        )

        BigHeading(
            text = stringResource(id = R.string.login_greeting),
            tag = loginGreeting
        )

        SmallHeading(
            text = stringResource(id = R.string.login_page_subtitle),
            tag = subtitle
        )

        ImaanInputFieldWithIcon(
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
            iconResId = com.imaan.design_system.R.drawable.ic_phone,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            focusManager = focusManager,
            placeHolder = stringResource(id = R.string.phone)
        )

        ImaanAppButton(
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

        Spacer(modifier = Modifier.weight(0.8f))

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
                },
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



