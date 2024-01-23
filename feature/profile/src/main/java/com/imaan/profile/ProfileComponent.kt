package com.imaan.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imaan.components.CircularImage
import com.imaan.user.UserModel
import com.imaan.user.dummyUser

@Composable
internal fun ProfileComponent(
    modifier: Modifier = Modifier,
    userModel: UserModel = dummyUser,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .size(250.dp),
            contentAlignment = Alignment.Center
        ) {
            CenteredArcs(
                modifier = Modifier
                    .size(230.dp)
            )
            CircularImage(
                modifier = Modifier
                    .size(150.dp)
                    .border(width = 0.dp, color = Color.Transparent),
                imageURL = userModel.profilePicUrl,
                onClick = {}
            )

        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Murtaza Khursheed",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.8f
                )
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(18.dp),
                painter = painterResource(id = com.imaan.resources.R.drawable.ic_phone),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            )
            Text(
                text = userModel.phone.value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            )
        }
    }
}

@Composable
fun CenteredArcs(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary.copy(
        alpha = 0.4f
    ),
    strokeWidth: Float = 4f
) {
    Canvas(modifier = modifier){
        drawCircle(
            color = color,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            radius = this.size.height * 0.4f,
            center = this.center
        )
        drawArc(
            color = color,
            startAngle = 10f,
            sweepAngle = 60f,
            topLeft = Offset.Zero,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
            )
        )

        drawArc(
            color = color,
            startAngle = 90f,
            sweepAngle = 80f,
            topLeft = Offset.Zero,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )

        drawArc(
            color = color,
            startAngle = 190f,
            sweepAngle = 140f,
            topLeft = Offset.Zero,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )


    }
}

@Preview
@Composable
fun ProfileComponentPreview() {
    ProfileComponent()
}