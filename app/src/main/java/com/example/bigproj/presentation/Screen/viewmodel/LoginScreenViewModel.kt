package com.example.bigproj.presentation.Screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigproj.domain.repository.AuthRepository
import com.example.bigproj.presentation.Screen.state.LoginScreenEvent
import com.example.bigproj.presentation.Screen.state.LoginScreenState
import com.example.bigproj.presentation.navigation.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set

    private val _events = Channel<AuthEvent>()
    val events = _events.receiveAsFlow()

    fun goToVerification() {
        viewModelScope.launch {
            sendCodeOnEmail()
            _events.send(AuthEvent.NavigateToVerification)
        }

    }

    fun goToRegistration() {
        viewModelScope.launch {
            _events.send(AuthEvent.NavigateToRegistration)
        }
    }

    fun sendCodeOnEmail() {
        viewModelScope.launch {
            authRepository.sendCodeOnEmail(email = state.email)
        }
    }

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailUpdated -> this.state = state.copy(email = event.newEmail)
            is LoginScreenEvent.NavigateToScreen -> when (event.screen) {
                is Screen.Verification -> goToVerification()
                is Screen.Register -> goToRegistration()
                else -> Unit
            }
        }
    }
}

sealed class AuthEvent {
    object NavigateToVerification : AuthEvent()
    object NavigateToRegistration : AuthEvent()
}