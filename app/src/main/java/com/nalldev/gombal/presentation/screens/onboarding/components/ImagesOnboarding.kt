package com.nalldev.gombal.presentation.screens.onboarding.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.nalldev.gombal.R

@Composable
fun ImagesOnBoarding(modifier: Modifier = Modifier, page: Int) {
    val transition = updateTransition(targetState = page, label = "ImagesOnBoarding")

    val images = listOf(
        R.drawable.onboarding_one,
        R.drawable.onboarding_two,
        R.drawable.onboarding_three
    )

    val rotations = images.indices.map { index ->
        val currentIndex = index + 1
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "RotationAnimation"
        ) { page ->
            when {
                currentIndex < page -> -90f
                currentIndex == page -> 0f
                else -> 90f
            }
        }
    }

    val translationsX = images.indices.map { index ->
        val currentIndex = index + 1
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "TranslationXAnimation"
        ) { page ->
            when {
                currentIndex < page -> -600f
                currentIndex == page -> 0f
                else -> 600f
            }
        }
    }

    val translationsY = images.indices.map { index ->
        val currentIndex = index + 1
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "TranslationYAnimation"
        ) { page ->
            when {
                currentIndex < page -> 800f
                currentIndex == page -> 0f
                else -> 800f
            }
        }
    }

    val alphas = images.indices.map { index ->
        val currentIndex = index + 1
        transition.animateFloat(
            transitionSpec = { tween(1300) },
            label = "AlphaAnimation"
        ) { page ->
            if (currentIndex == page) 1f else 0f
        }
    }

    images.forEachIndexed { index, image ->
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .offset {
                    IntOffset(
                        x = translationsX[index].value.dp
                            .toPx()
                            .toInt(),
                        y = translationsY[index].value.dp
                            .toPx()
                            .toInt()
                    )
                }
                .rotate(rotations[index].value)
                .alpha(alphas[index].value)
        )
    }
}