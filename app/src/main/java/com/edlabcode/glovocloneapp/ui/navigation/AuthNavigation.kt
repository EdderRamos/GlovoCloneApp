package com.edlabcode.glovocloneapp.ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.edlabcode.glovocloneapp.ui.auth.login.LoginScreen
import com.edlabcode.glovocloneapp.ui.auth.login.LoginViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object AuthNavigation

@Serializable
data object Login

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    navigation<AuthNavigation>(startDestination = Login) {
        composable<Login> {

            val viewModel = koinViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LoginScreen(state, onEvent = viewModel::onEvent)
        }
    }
}