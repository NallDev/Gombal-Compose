package com.nalldev.gombal.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.presentation.screens.home.HomeScreen
import com.nalldev.gombal.presentation.screens.home.HomeViewModel
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingScreen
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingViewModel
import com.nalldev.gombal.utils.canGoBack
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun NavGraph(navController : NavHostController = rememberNavController(), startDestination : Screen) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.OnBoarding> {
            val viewModel = koinViewModel<OnBoardingViewModel>()
            OnBoardingScreen(onClick = {
                navController.navigate(Screen.Home) {
                    popUpTo(Screen.OnBoarding) {
                        inclusive = true
                    }
                }
            }, viewModel = viewModel)
        }
        composable<Screen.Home> {
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                navigateToDetail = { jobModel ->
                navController.navigate(Screen.Detail(jobModel))
            }, navigateToProfile = {}, navigateToBookmark = {})
        }
        composable<Screen.Detail>(typeMap = mapOf(typeOf<JobModel>() to JobType)) {
            val detail = it.toRoute<Screen.Detail>()
            DetailScreen(
                user = detail.jobModel.title,
                onClick = {
                    if (navController.canGoBack) {
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}

@Composable
fun DetailScreen(modifier: Modifier = Modifier, user : String, onClick: () -> Unit) {
    Scaffold { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding).clickable(onClick = onClick), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = user)
        }
    }
}