package com.nalldev.gombal.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nalldev.gombal.R
import com.nalldev.gombal.presentation.navigation.NavGraph
import com.nalldev.gombal.presentation.navigation.Screen
import com.nalldev.gombal.ui.theme.GombalTheme
import com.nalldev.gombal.utils.CommonHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CommonHelper.setDarkMode(this@MainActivity, viewModel.isDarkMode.value)
        val installSplashScreen = installSplashScreen()
        installSplashScreen.setKeepOnScreenCondition(condition = { true })

        setContent {
            val isDarkMode by viewModel.isDarkMode.collectAsState()
            val isOnBoardingFinished by viewModel.isOnBoardingFinished.collectAsState()

            LaunchedEffect(isDarkMode) {
                enableEdgeToEdge(statusBarStyle = SystemBarStyle.auto(
                    lightScrim = getColor(R.color.colorPrimary),
                    darkScrim = getColor(R.color.colorPrimary),
                    detectDarkMode = { isDarkMode }
                ))
            }

            LaunchedEffect(isOnBoardingFinished) {
                installSplashScreen.setKeepOnScreenCondition(condition = { false })
            }

            GombalTheme {
                NavGraph(startDestination = if (isOnBoardingFinished) Screen.Home else Screen.OnBoarding, isDarkMode = isDarkMode)
            }
        }
    }
}