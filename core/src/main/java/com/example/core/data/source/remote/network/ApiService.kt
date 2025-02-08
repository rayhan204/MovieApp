package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/now_playing")
    suspend fun getList(
        @Query("api_key") apiKey: String
    ): ListMovieResponse
}