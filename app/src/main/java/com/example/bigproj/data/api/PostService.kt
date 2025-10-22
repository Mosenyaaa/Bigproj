package com.example.bigproj.data.api

import com.example.bigproj.data.model.*
import PostResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("/api/general/healthcheck")
    suspend fun getAllPosts(): Response<List<PostResponseDto>>

}