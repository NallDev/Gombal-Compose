package com.nalldev.gombal.presentation.screens.home.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nalldev.gombal.R

@Composable
fun ThemeToggleButton(modifier: Modifier = Modifier, onClick: () -> Unit, isChecked: Boolean) {
    val transition = updateTransition(targetState = isChecked, label = "ThemeToggleTransition")

    val backgroundOneColor by transition.animateColor(
        label = "BackgroundOneColorAnimation"
    ) { checked ->
        if (checked) Color(0xFF18005E) else Color(0xFFFFC8A9)
    }

    val backgroundTwoColor by transition.animateColor(
        label = "BackgroundTwoColorAnimation"
    ) { checked ->
        if (checked) Color(0xFF8E72FF) else Color(0xFFFFC7DE)
    }

    val sunPosition by transition.animateDp(
        label = "SunPositionAnimation"
    ) { checked ->
        if (checked) 36.dp else 4.dp
    }

    val moonPosition by transition.animateDp(
        label = "MoonPositionAnimation"
    ) { checked ->
        if (checked) 36.dp else 4.dp
    }

    val cloudOnePosition by transition.animateDp(
        label = "CloudOnePositionAnimation"
    ) { checked ->
        if (checked) 44.dp else 4.dp
    }

    val cloudTwoPosition by transition.animateDp(
        label = "CloudTwoPositionAnimation"
    ) { checked ->
        if (checked) 30.dp else 16.dp
    }

    val sunAlpha by transition.animateFloat(
        label = "SunAlphaAnimation"
    ) { checked ->
        if (checked) 0f else 1f
    }

    Card(modifier = modifier.clip(RoundedCornerShape(50))) {
        Box(
            modifier = modifier
                .height(32.dp)
                .width(64.dp)
                .background(brush = Brush.linearGradient(
                    colors = listOf(
                        backgroundOneColor,
                        backgroundTwoColor
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(0f, 0f)
                ))
                .clickable(onClick = onClick),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_sun),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = sunPosition)
                    .size(24.dp)
                    .zIndex(1f)
                    .align(Alignment.CenterStart)
                    .alpha(sunAlpha)
            )

            Image(
                painter = painterResource(R.drawable.ic_moon),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = moonPosition)
                    .size(24.dp)
                    .align(Alignment.CenterStart)
            )

            Image(
                painter = painterResource(R.drawable.ic_cloud_one),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp, end = cloudOnePosition)
                    .size(16.dp)
                    .align(Alignment.TopEnd)
            )

            Image(
                painter = painterResource(R.drawable.ic_cloud_two),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp, end = cloudTwoPosition)
                    .size(16.dp)
                    .zIndex(1f)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview
@Composable
private fun ThemeToggleButtonLightPreview() {
    ThemeToggleButton(onClick = {}, isChecked = false)
}

@Preview
@Composable
private fun ThemeToggleButtonDarkPreview() {
    ThemeToggleButton(onClick = {}, isChecked = true)
}