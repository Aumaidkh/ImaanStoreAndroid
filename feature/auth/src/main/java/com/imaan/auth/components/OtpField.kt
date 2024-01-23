package com.imaan.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.common.model.OTP

@Composable
fun ImaanAppOtpField(
    modifier: Modifier = Modifier,
    otp: OTP = OTP(""),
    onOTPChanged: (String) -> Unit = {},
    size: Int = 4,
    boxSize: Dp = 60.dp,
) {
    val focusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = otp.value,
        onValueChange = {
             if(it.length<=size){
                 onOTPChanged(it)
                 if (it.length == size){
                     localFocusManager.clearFocus()
                 }
             }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        decorationBox = {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(size){ index ->
                    val char = when {
                        index >= otp.value.length -> ""
                        else -> otp.value[index].toString()
                    }
                    val isFocused = otp.value.length == index
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(boxSize),
                        shape = MaterialTheme.shapes.medium,
                        color = if (char.isNotEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                        border = BorderStroke(
                            width = if (isFocused) 1.dp else 0.dp,
                            color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = char,
                                style = TextStyle(
                                    color = if (char.isEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 23.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun OtpFieldPreview() {
    ImaanAppOtpField()
}