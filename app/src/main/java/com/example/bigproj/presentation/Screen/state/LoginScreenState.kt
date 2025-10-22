package com.example.bigproj.presentation.Screen.state

sealed class LoginScreenEvent {
    data class EmailUpdated(val newEmail: String): LoginScreenEvent()
}

data class LoginScreenState(
    var email: String = ""
)