package com.imaan.tracking

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.imaan.util.toDayName
import com.imaan.util.toDayOfMonth
import com.imaan.util.toMonthName
import java.util.Date

@Composable
fun EstimatedDeliveryDateView(
    modifier: Modifier = Modifier,
    date: Date = Date()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estimated Delivery",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = date.toDayName(),
            style = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        val month = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
            ){
                append("${date.toDayOfMonth()} ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            ){
                append(date.toMonthName())
            }

        }
        Text(
            text = month,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun EstimatedDeliveryDateViewPreview() {
    EstimatedDeliveryDateView()
}