package com.nalldev.gombal.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nalldev.gombal.presentation.navigation.NavGraph
import com.nalldev.gombal.presentation.navigation.Screen
import com.nalldev.gombal.ui.theme.GombalTheme
import com.nalldev.gombal.utils.CommonHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val installSplashScreen = installSplashScreen()
        installSplashScreen.setKeepOnScreenCondition(condition = { true })
        initObserver(installSplashScreen)
    }

    private fun initObserver(installSplashScreen: SplashScreen) = with(viewModel) {
        isDarkMode.observe(this@MainActivity) { isDarkMode ->
            CommonHelper.setDarkMode(this@MainActivity.applicationContext, isDarkMode)
        }
        isOnBoardingFinished.observe(this@MainActivity) { isOnBoardingFinished ->
            setContent {
                GombalTheme {
                    NavGraph(startDestination = if (isOnBoardingFinished) Screen.Home else Screen.OnBoarding)
                }
            }
            installSplashScreen.setKeepOnScreenCondition(condition = { false })
        }
    }
}