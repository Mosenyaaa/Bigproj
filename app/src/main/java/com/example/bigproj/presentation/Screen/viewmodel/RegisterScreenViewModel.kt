package com.example.bigproj.presentation.Screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.bigproj.presentation.Screen.state.RegisterScreenEvent
import com.example.bigproj.presentation.Screen.state.RegisterScreenState

class RegisterScreenViewModel : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.EmailUpdated -> this.state = state.copy(email = event.newEmail)
            is RegisterScreenEvent.NameUpdated -> this.state = state.copy(name = event.newName)

        }
    }
}