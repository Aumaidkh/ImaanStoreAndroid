package com.imaan.auth.verify_otp

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imaan.auth.components.ImaanAppOtpField
import com.imaan.auth.utils.TestTags.verifyOtpScreen
import com.imaan.design_system.components.buttons.ImaanAppButton

@Composable
fun VerifyOtpScreen(
    state: VerifyOtpUiState = VerifyOtpUiState(),
    onOtpChange: (String) -> Unit = {},
    onVerify: () -> Unit = {},
    onResendOtp: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
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
            val model = ImageRequest
                .Builder(LocalContext.current)
                .data(com.imaan.design_system.R.drawable.illustration_verify_otp)
                .build()
            AsyncImage(
                model = model,
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

        ImaanAppOtpField(
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

        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            text = "Verify",
            loading = state.loading,
            onClick = onVerify,
            enabled = state.otp.value.length == 4
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