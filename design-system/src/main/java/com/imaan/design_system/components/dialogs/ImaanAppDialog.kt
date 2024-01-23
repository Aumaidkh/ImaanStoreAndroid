package com.imaan.design_system.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.imaan.design_system.components.buttons.ButtonType
import com.imaan.design_system.components.buttons.ImaanAppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImaanActionDialog(
    title: String,
    body: String,
    positiveButtonText: String = "Okay",
    negativeButtonText: String? = "Cancel",
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties()
    ) {
        Card(onClick = { /*TODO*/ }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
                ImaanAppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = positiveButtonText,
                    onClick = onPositiveButtonClick,
                    type = ButtonType.Filled
                )
                Spacer(modifier = Modifier.height(16.dp))
                negativeButtonText?.let {
                    ImaanAppButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onNegativeButtonClick() },
                        text = it,
                        type = ButtonType.Outlined
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    ImaanActionDialog(title = "Delete Account",
        body = "Are you sure you want to delete your account?. This operation can't be undone",
        positiveButtonText = "Yes",
        negativeButtonText = "No",
        onPositiveButtonClick = {},
        onNegativeButtonClick = {},
        onDismissRequest = {}

    )
}