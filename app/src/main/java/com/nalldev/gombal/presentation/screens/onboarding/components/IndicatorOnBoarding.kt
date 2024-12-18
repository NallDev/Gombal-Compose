package com.nalldev.gombal.presentation.screens.onboarding.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nalldev.gombal.ui.theme.CustomColorTheme

@Composable
fun IndicatorOnBoarding(modifier: Modifier = Modifier, page: Int, isDarkMode: Boolean) {
    val transition = updateTransition(targetState = page, label = "IndicatorOnBoarding")
    val activeWidth = 64.dp
    val inactiveWidth = 40.dp

    val widths = List(3) { index ->
        transition.animateDp(
            transitionSpec = {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            },
            label = "WidthAnimation"
        ) { page ->
            val currentIndex = index + 1
            if (currentIndex == page) activeWidth else inactiveWidth
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        widths.forEachIndexed { index, _ ->
            val currentIndex = index + 1
            Box(
                modifier = Modifier
                    .width(widths[index].value)
                    .height(8.dp)
                    .padding(horizontal = 2.dp)
                    .background(
                        color = if (currentIndex == page) CustomColorTheme.colorPrimary(isDarkMode) else CustomColorTheme.gray(isDarkMode),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}