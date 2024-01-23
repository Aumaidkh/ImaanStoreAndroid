package com.imaan.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imaan.common.model.Amount
import com.imaan.total.TotalModel


@Composable
private fun TotalItemComponent(
    modifier: Modifier = Modifier,
    total: Amount,
    label: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            ),
        )
        Text(
            text = total.inRupees,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
internal fun TotalComponent(totals: TotalModel,
                           paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        PromoComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        )
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.subtotal, label = "Subtotal")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.deliveryCharges, label = "Delivery charges")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.discount, label = "Discount")
        TotalItemComponent(modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp), total = totals.grandTotal, label = "Grand Total")
        Divider(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
                .fillMaxWidth(),
            thickness = 0.7.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.3f
            )
        )
    }
}