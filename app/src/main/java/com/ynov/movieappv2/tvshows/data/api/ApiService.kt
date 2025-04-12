package com.ynov.movieappv2.tvshows.data.api

import com.ynov.movieappv2.tvshow_details.data.dto.ShowDetailsResponse
import com.ynov.movieappv2.tvshows.data.dto.PopularShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/most-popular")
    suspend fun getPopularShows(@Query("page") page: Int = 1): PopularShowsResponse

    @GET("api/show-details")
    suspend fun getShowDetails(@Query("q") show: String): ShowDetailsResponse
}