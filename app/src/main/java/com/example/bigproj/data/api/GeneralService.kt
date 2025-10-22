package com.example.bigproj.data.api

import PostResponseDto
import com.example.bigproj.data.model.SendCodeOnEmailResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GeneralService {

    @GET("/api/general/healthcheck")
    suspend fun getAllPosts(): Response<PostResponseDto>

    @Headers("apikey: apikeyvaluec9a9e9b1b42a4967a19e9c85cc458d93707abae3ef3e4926afee782d9d82b2ca72e15abe693742fb9fee5282282a58771760387425230766")
    @GET("/api/general/send_register_or_authenticate_verification_code_on_email")
    suspend fun sendCodeOnEmail(
        @Query("email") email: String
    ): Response<SendCodeOnEmailResponseDto>
}