package com.imaan.store.feature_auth.presentation.verify_otp

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.store.R
import com.imaan.store.design_system.composables.LoadingButton
import com.imaan.store.core.presentation.utils.UiEvent
import com.imaan.store.feature_auth.presentation.login.OtpField
import com.imaan.store.feature_auth.presentation.utils.TestTags.verifyOtpScreen

@Composable
fun VerifyOtpScreen(
    state: VerifyOtpUiState = VerifyOtpUiState(),
    event: UiEvent? = null,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onOtpChange: (String) -> Unit = {},
    onNavigateToHomeScreen: () -> Unit = {},
    onVerify: () -> Unit = {},
    onResendOtp: () -> Unit = {}
) {
    LaunchedEffect(key1 = event){
        when(event){
            is UiEvent.Error -> {
                snackbarHostState.showSnackbar(
                    message = event.errorMessage
                )
            }
            is UiEvent.Success<*> -> {
                onNavigateToHomeScreen()
            }
            null -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .semantics {
                testTag = verifyOtpScreen
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.illustration_verify_otp),
                contentDescription = "verifyOtp"
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 24.dp),
            text = "Verification",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
        )

        Text(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth(0.7f),
            text = "A verification code was sent to your phone number",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            ),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )

        OtpField(
            boxSize = 50.dp,
            otp = state.otp,
            onOTPChanged = onOtpChange
        )

        Text(
            modifier = Modifier
                .clickable(enabled = state.showResendOtp){
                    if (state.showResendOtp) {
                        onResendOtp()
                    }
                }
                .padding(
                    top = 50.dp,
                    bottom = 50.dp
                ),
            text = state.otpStatusMessage,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (state.showResendOtp) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            ),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )

        LoadingButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            text = "Verify",
            loading = state.loading,
            onClick = onVerify,
            enabled = state.otp.length == 4
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun VerifyOtpScreenDarkModePreview() {
    VerifyOtpScreen()
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun VerifyOtpScreenLightModePreview() {
    VerifyOtpScreen()
}