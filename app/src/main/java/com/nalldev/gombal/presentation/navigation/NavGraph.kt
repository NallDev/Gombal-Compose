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
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nalldev.gombal.R
import com.nalldev.gombal.utils.canGoBack
import kotlin.reflect.typeOf

@Composable
fun NavGraph(navController : NavHostController = rememberNavController(), startDestination : Screen = Screen.Home) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home> {
            HomeScreen(onClick = {
                navController.navigate(Screen.Detail(User(1, "Nall")))
            })
        }
        composable<Screen.Detail>(typeMap = mapOf(typeOf<User>() to UserType)) {
            val detail = it.toRoute<Screen.Detail>()
            DetailScreen(
                user = detail.user.id,
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
fun HomeScreen(modifier: Modifier = Modifier, onClick : () -> Unit) {
    Scaffold { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding).clickable(onClick = onClick), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "HOME", color = colorResource(R.color.white))
        }
    }
}

@Composable
fun DetailScreen(modifier: Modifier = Modifier, user : Int, onClick: () -> Unit) {
    Scaffold { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding).clickable(onClick = onClick), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = user.toString())
        }
    }
}