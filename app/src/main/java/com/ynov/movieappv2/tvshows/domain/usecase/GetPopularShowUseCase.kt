package com.ynov.movieappv2.tvshows.domain.usecase

import com.ynov.movieappv2.tvshows.domain.model.PopularShow
import com.ynov.movieappv2.tvshows.domain.repository.PopularShowsRepository

class GetPopularShowsUseCase(private val repository: PopularShowsRepository) {
    suspend operator fun invoke(): List<PopularShow> {
        return repository.getPopularShows()
    }
}