package com.ynov.movieappv2.tvshows.data.repository

import com.ynov.movieappv2.tvshows.data.api.ApiClient
import com.ynov.movieappv2.tvshows.data.dto.toPopularShow
import com.ynov.movieappv2.tvshows.domain.model.PopularShow
import com.ynov.movieappv2.tvshows.domain.repository.PopularShowsRepository

class PopularShowsRepositoryImpl : PopularShowsRepository {
    override suspend fun getPopularShows(): List<PopularShow> {
        return ApiClient.apiService.getPopularShows().tv_shows.map { it.toPopularShow() }
    }
}
