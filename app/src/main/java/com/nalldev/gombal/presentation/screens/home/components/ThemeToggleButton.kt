package com.nalldev.gombal.presentation.screens.home.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nalldev.gombal.R

@Composable
fun ThemeToggleButton(modifier: Modifier = Modifier, onClick: () -> Unit, isChecked: Boolean) {
    val transition = updateTransition(targetState = isChecked, label = "ThemeToggleTransition")

    val backgroundColor by transition.animateColor(
        label = "BackgroundColorAnimation"
    ) { checked ->
        if (checked) Color(0xFF6A5ACD) else Color(0xFFFFDAB9)
    }

    val sunPosition by transition.animateIntOffset(
        label = "SunPositionAnimation"
    ) { checked ->
        if (checked) IntOffset(x = 72, y = 0) else IntOffset(x = 8, y = 0)
    }

    val moonPosition by transition.animateIntOffset(
        label = "MoonPositionAnimation"
    ) { checked ->
        if (checked) IntOffset(x = 72, y = 0) else IntOffset(x = 8, y = 0)
    }

    val cloudOnePosition by transition.animateIntOffset(
        label = "CloudOnePositionAnimation"
    ) { checked ->
        if (checked) IntOffset(x = -80, y = 0) else IntOffset(x = -16, y = 0)
    }

    val cloudTwoPosition by transition.animateIntOffset(
        label = "CloudTwoPositionAnimation"
    ) { checked ->
        if (checked) IntOffset(x = -64, y = 16) else IntOffset(x = -32, y = 16)
    }

    val sunAlpha by transition.animateFloat(
        label = "SunAlphaAnimation"
    ) { checked ->
        if (checked) 0f else 1f
    }

    Box(
        modifier = modifier
            .height(32.dp)
            .width(64.dp)
            .background(backgroundColor, RoundedCornerShape(50))
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_sun),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .zIndex(1f)
                .align(Alignment.CenterStart)
                .offset { sunPosition }
                .alpha(sunAlpha)
        )

        Image(
            painter = painterResource(R.drawable.ic_moon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
                .offset { moonPosition }
        )

        Image(
            painter = painterResource(R.drawable.ic_cloud_one),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.TopEnd)
                .offset { cloudOnePosition }
        )

        Image(
            painter = painterResource(R.drawable.ic_cloud_two),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .zIndex(1f)
                .align(Alignment.TopEnd)
                .offset { cloudTwoPosition }
        )
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