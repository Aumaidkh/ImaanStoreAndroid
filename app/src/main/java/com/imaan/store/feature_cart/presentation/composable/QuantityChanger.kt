package com.imaan.store.feature_cart.presentation.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imaan.store.R
import com.imaan.store.design_system.composables.CircularIcon


@Composable
fun QuantityChanger(
    modifier: Modifier = Modifier,
    quantity: Int,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .clickable{
                    onIncreaseClick()
                }
        ){
           Icon(
               painter = painterResource(id = R.drawable.ic_plus),
               contentDescription = "Add",
               modifier = Modifier
                   .padding(6.dp),
               tint = MaterialTheme.colorScheme.primary
           )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = quantity.toString(),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .clip(shape = MaterialTheme.shapes.extraSmall)
                .clickable(enabled = quantity>0){
                    onDecreaseClick()
                }
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "Add",
                modifier = Modifier
                    .padding(6.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
