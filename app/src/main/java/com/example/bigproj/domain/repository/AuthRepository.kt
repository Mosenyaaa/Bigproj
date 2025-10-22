package com.example.bigproj.domain.repository

import com.example.bigproj.data.RetrofitClient
import com.example.bigproj.data.api.GeneralService

class AuthRepository(
    val generalServiceApi: GeneralService = RetrofitClient.apiService
) {
    suspend fun sendCodeOnEmail(email: String) {
        generalServiceApi.sendCodeOnEmail(email)
    }
}