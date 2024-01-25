package com.imaan.payments.confirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.imaan.common.wrappers.Result
import com.imaan.design_system.components.buttons.ButtonType
import com.imaan.design_system.components.buttons.ImaanAppButton
import com.imaan.payments.R

@Composable
fun PaymentConfirmationScreen(
    paymentResult: Result<String>,
    paddingValues: PaddingValues,
    onTryAgain: () -> Unit,
    onContinueShopping: () -> Unit,
    onShare: () -> Unit
) {
    when (paymentResult) {
        is Result.Success -> {
            PaymentSuccessfulScreen(
                paddingValues = paddingValues,
                onContinueShopping = onContinueShopping,
                onShare = onShare
            )
        }

        else -> {
            PaymentFailedScreen(
                paddingValues = paddingValues,
                onTryAgain = onTryAgain
            )
        }
    }
}

@Composable
private fun PaymentSuccessfulScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onContinueShopping: () -> Unit,
    onShare: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_success))
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        LottieAnimation(
            modifier = Modifier
                .size(250.dp)
                .padding(horizontal = 48.dp),
            composition = composition,
            iterations = 1,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(0.6f))
        Text(
            text = "Order Placed Successfully",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Thank you for placing an order. You will receive an email confirmation shortly",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Continue Shopping",
            backgroundColor = MaterialTheme.colorScheme.onPrimary,
            foregroundColor = MaterialTheme.colorScheme.primary,
            onClick = onContinueShopping
        )
        Spacer(modifier = Modifier.height(24.dp))
        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Share",
            type = ButtonType.Outlined,
            backgroundColor = MaterialTheme.colorScheme.onPrimary,
            foregroundColor = MaterialTheme.colorScheme.onPrimary,
            onClick = onShare
        )
    }
}

@Composable
private fun PaymentFailedScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onTryAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_error))
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        LottieAnimation(
            modifier = Modifier
                .size(250.dp)
                .padding(horizontal = 48.dp),
            composition = composition,
            iterations = 1,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(0.6f))
        Text(
            text = "Payment Failed",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Something went wrong while making payment. Please try after some time",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))
        ImaanAppButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Try Again",
            backgroundColor = MaterialTheme.colorScheme.primary,
            foregroundColor = MaterialTheme.colorScheme.onPrimary,
            onClick = onTryAgain
        )

    }
}
