package com.nalldev.gombal.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.presentation.screens.about.AboutScreen
import com.nalldev.gombal.presentation.screens.bookmark.BookmarkScreen
import com.nalldev.gombal.presentation.screens.bookmark.BookmarkViewModel
import com.nalldev.gombal.presentation.screens.detail.DetailScreen
import com.nalldev.gombal.presentation.screens.detail.DetailViewModel
import com.nalldev.gombal.presentation.screens.home.HomeScreen
import com.nalldev.gombal.presentation.screens.home.HomeViewModel
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingScreen
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), startDestination: Screen) {
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
                }, navigateToProfile = {
                    navController.navigate(Screen.About)
                }, navigateToBookmark = {
                    navController.navigate(Screen.Bookmark)
                }
            )
        }
        composable<Screen.Detail>(typeMap = mapOf(typeOf<JobModel>() to JobType)) {
            val viewModel = koinViewModel<DetailViewModel>()
            val uriHandler = LocalUriHandler.current
            DetailScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                },
                onApplyClick = { url ->
                    uriHandler.openUri(Uri.parse(url).toString())
                }
            )
        }
        composable<Screen.Bookmark> {
            val viewModel = koinViewModel<BookmarkViewModel>()
            BookmarkScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                },
                navigateToDetail = { jobModel ->
                    navController.navigate(Screen.Detail(jobModel))
                }
            )
        }
        composable<Screen.About> {
            AboutScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}