package com.example.bigproj.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bigproj.presentation.navigation.MainNav
import com.example.bigproj.presentation.navigation.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BigProjApp()
        }
    }
}

@Composable
fun BigProjApp() {
    val navController = rememberNavController()

    MainNav(
        navHostController = navController,
        startDestination = Screen.Login
    )
}