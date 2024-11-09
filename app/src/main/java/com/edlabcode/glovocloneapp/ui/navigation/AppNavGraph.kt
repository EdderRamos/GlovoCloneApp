package com.edlabcode.glovocloneapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edlabcode.glovocloneapp.ui.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
data object Splash

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Splash) {

        composable<Splash> {
            SplashScreen()
        }
    }
}