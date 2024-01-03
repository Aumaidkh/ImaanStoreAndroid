package com.imaan.store.feature_auth.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.imaan.store.feature_auth.presentation.utils.TestTags

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .semantics {
                testTag = TestTags.registerScreen
            }
            .fillMaxSize()
    ) {

    }
}