package com.nalldev.gombal.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nalldev.gombal.R
import com.nalldev.gombal.presentation.screens.onboarding.components.ButtonOnBoarding
import com.nalldev.gombal.presentation.screens.onboarding.components.ImagesOnBoarding
import com.nalldev.gombal.presentation.screens.onboarding.components.IndicatorOnBoarding
import com.nalldev.gombal.presentation.screens.onboarding.components.SubtitleOnBoarding
import com.nalldev.gombal.presentation.screens.onboarding.components.TitleOnBoarding
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val page by viewModel.page.collectAsState()
    val isTransitioning = viewModel.isTransitioning
    val onBoardingFinished = viewModel.onBoardingFinished

    LaunchedEffect(page) {
        if (page > 1) {
            delay(1400)
            viewModel.finishTransition()
        }
    }

    LaunchedEffect(onBoardingFinished) {
        if (onBoardingFinished) {
            viewModel.setOnBoardingFinished()
            onClick()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.onBoardingBackground))
    ) {
        ImagesOnBoarding(modifier = modifier, page = page)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(312.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = colorResource(R.color.colorBackground),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                IndicatorOnBoarding(page = page)

                Spacer(modifier = Modifier.height(24.dp))

                TitleOnBoarding(modifier = modifier, page = page)

                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    SubtitleOnBoarding(modifier = modifier, page = page)
                }
            }

            ButtonOnBoarding(
                modifier = modifier.align(Alignment.BottomEnd),
                page = page,
                onClick = { if (!isTransitioning) viewModel.nextPage() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {
    OnBoardingScreen(onClick = {}, viewModel = koinViewModel())
}