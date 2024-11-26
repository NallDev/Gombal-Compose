package com.nalldev.gombal.presentation.screens.onboarding.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nalldev.gombal.R

@Composable
fun ButtonOnBoarding(modifier: Modifier = Modifier, page: Int, onClick: () -> Unit) {
    val transition = updateTransition(targetState = page, label = "Button On Boarding")

    val width by transition.animateFloat(
        label = "ButtonWidth",
        transitionSpec = { tween(durationMillis = 800) }
    ) { page ->
        if (page == 3) {
            1.0f
        } else {
            0.4f
        }
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.colorPrimary)),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(width)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (page == 3) "Get Started" else "Next",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (page != 3) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}