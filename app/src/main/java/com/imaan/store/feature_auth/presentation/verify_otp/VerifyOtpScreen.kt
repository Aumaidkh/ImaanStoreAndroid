package com.imaan.store.feature_auth.presentation.verify_otp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.imaan.store.feature_auth.presentation.utils.TestTags.verifyOtpScreen

@Composable
fun VerifyOtpScreen() {
    Column(
        modifier = Modifier
            .semantics {
                testTag = verifyOtpScreen
            }
    ) {

    }
}