package com.imaan.store.feature_home.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.store.R
import com.imaan.store.design_system.ui.theme.ImaanTheme

@Composable
fun ProfileComponent(
    modifier: Modifier = Modifier,
    user: String = "Hopcape"
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = Color(0xff76b536))
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    shape = CircleShape
                )
                .size(100.dp),
            contentAlignment = Alignment.Center
        ){
//            Text(
//                text = user.first().toString(),
//                color = Color.White,
//                style = MaterialTheme.typography.bodySmall.copy(
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 23.sp
//                )
//            )
            Image(
                painter = painterResource(id = R.drawable.dummy_pic),
                contentDescription = ""
            )
        }
        Text(
            text = user,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileComponentPreview(){
   ImaanTheme {
       ProfileComponent()
   }
}