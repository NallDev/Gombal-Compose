package com.nalldev.gombal.presentation.screens.onboarding.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R

@Composable
fun SubtitleOnBoarding(modifier: Modifier = Modifier, page: Int) {
    val transition = updateTransition(targetState = page, label = "Subtitles On Boarding")

    val subtitles = listOf(
        "Find your ideal job faster and more efficiently. Our platform filters opportunities that match your skills and interests, allowing you to focus on what truly matters.",
        "Seamlessly apply for jobs no matter where you are or what time it is. With our platform, your next career move is just a few clicks away, anytime you’re ready.",
        "Unlock endless career opportunities tailored just for you. Start your journey today and discover roles that align perfectly with your skills and passions."
    )

    val translationsX = subtitles.indices.map { index ->
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "TranslationX Animation"
        ) { page ->
            val currentIndex = index + 1
            when {
                currentIndex < page -> -600f
                currentIndex == page -> 0f
                else -> 600f
            }
        }
    }

    val alphas = subtitles.indices.map { index ->
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "Alpha Animation"
        ) { page ->
            val currentIndex = index + 1
            if (currentIndex == page) 1f else 0f
        }
    }

    subtitles.forEachIndexed { index, subtitle ->
        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = colorResource(R.color.subtitle),
            modifier = modifier
                .offset { IntOffset(
                    x = translationsX[index].value.dp.toPx().toInt(),
                    y = 0
                )}
                .alpha(alphas[index].value)
        )
    }
}