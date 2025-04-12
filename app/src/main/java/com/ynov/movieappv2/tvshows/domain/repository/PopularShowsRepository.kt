package com.ynov.movieappv2.tvshows.domain.repository

import com.ynov.movieappv2.tvshows.domain.model.PopularShow

interface PopularShowsRepository {
    suspend fun getPopularShows(): List<PopularShow>
}