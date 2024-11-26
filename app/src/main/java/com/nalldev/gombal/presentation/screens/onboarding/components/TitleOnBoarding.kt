package com.nalldev.gombal.presentation.screens.onboarding.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nalldev.gombal.R
import kotlinx.coroutines.delay

@Composable
fun TitleOnBoarding(modifier: Modifier = Modifier, page: Int) {
    val titles = listOf(
        "Search job easier & more effective",
        "Apply for job anywhere & anytime",
        "Your dream job is waiting for you!"
    )

    val maxDurationMillis = 500L

    val text = remember { mutableStateOf("") }
    val currentTitle = titles[page-1]
    val showCursor = remember { mutableStateOf(true) }

    val delayPerChar = maxDurationMillis / currentTitle.length

    LaunchedEffect(key1 = page) {
        text.value = ""
        currentTitle.forEach { char ->
            delay(delayPerChar)
            text.value += char
        }
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(maxDurationMillis)
            showCursor.value = !showCursor.value
        }
    }

    Text(
        text = text.value + if (showCursor.value) "|" else "",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colorResource(R.color.colorOnBackground),
        modifier = modifier
    )
}