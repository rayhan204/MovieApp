package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/now_playing")
    suspend fun getList(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ListMovieResponse
}