package com.ynov.movieappv2.tvshows.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.episodate.com/") // ✅ juste la racine
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}