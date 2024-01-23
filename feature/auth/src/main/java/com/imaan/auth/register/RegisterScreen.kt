package com.imaan.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.auth.R
import com.imaan.auth.components.AuthIllustration
import com.imaan.auth.components.BigHeading
import com.imaan.auth.components.SmallHeading
import com.imaan.auth.utils.TestTags
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.design_system.components.input_fields.ImaanInputFieldWithIcon

@Composable
fun RegisterScreen(
    state: RegisterScreenUiState = RegisterScreenUiState(),
    onFullNameChange: (String) -> Unit = {},
    onPhoneChange: (String) -> Unit = {},
    onRegister: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues()
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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
            imageResId = com.imaan.design_system.R.drawable.masjid
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
        ImaanInputFieldWithIcon(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.fullNameField
                }
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            value = state.fullName.value,
            error = state.fullName.error,
            onValueChange = onFullNameChange,
            iconResId = com.imaan.design_system.R.drawable.ic_person,
            placeHolder = stringResource(id = R.string.full_name),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

        ImaanInputFieldWithIcon(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.phoneNumberField
                }
                .fillMaxWidth()
                .padding(
                    bottom = 40.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            value = state.phoneNumber.value,
            error = state.phoneNumber.error,
            onValueChange = onPhoneChange,
            iconResId = com.imaan.design_system.R.drawable.ic_phone,
            placeHolder = stringResource(id = R.string.phone),
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            maxLength = 10
        )

        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                )
                .testTag(TestTags.registerButton),
            text = stringResource(id = R.string.register),
            onClick = onRegister,
            loading = state.loading,
            enabled = state.buttonEnabled
        )
        
        Spacer(modifier = Modifier.weight(1f))

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
                .weight(0.05f)
        )
        Text(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.alreadyHaveAccount
                },
            text = stringResource(R.string.already_have_account),
        )

        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )
        TextButton(
            modifier = Modifier
                .semantics {
                    testTag = TestTags.loginText
                },
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
        Spacer(
            Modifier
                .windowInsetsBottomHeight(WindowInsets.systemBars)
                .padding(24.dp)
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
