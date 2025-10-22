package com.example.bigproj.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bigproj.presentation.Screen.LoginScreen
import com.example.bigproj.presentation.Screen.RegisterScreen
import com.example.bigproj.presentation.Screen.main.MainScreen
import com.example.bigproj.presentation.Screen.VerificationScreen
import com.example.bigproj.presentation.Screen.viewmodel.LoginScreenViewModel
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object  Login: Screen()

    @Serializable
    data object  Register: Screen()

    @Serializable
    data object Main : Screen()
    
    @Serializable
    data object Verification : Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: Screen.Login
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Login
    ) {
        composable<Screen.Login> {

            LoginScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }
        composable<Screen.Register> {
            RegisterScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }
        composable<Screen.Main> {
            MainScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }
        composable<Screen.Verification> {
            VerificationScreen(
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                },
                email = "msenyaaa@gmail.com", // Добавьте реальный email
                onVerificationComplete = {
                    // Переход на главный экран после верификации
                    navHostController.navigate(Screen.Main)
                },
                onDifferentEmail = {
                    // Возврат к вводу email
                    navHostController.navigate(Screen.Login)
                }
            )
        }
    }
}