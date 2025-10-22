package com.example.bigproj.presentation.Screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.bigproj.data.RetrofitClient
import com.example.bigproj.presentation.navigation.Screen
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isOk by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            isOk = RetrofitClient.apiService.getAllPosts().isSuccessful
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = isOk.toString()
        )
    }
}