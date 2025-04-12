package com.ynov.movieappv2.tvshow_details.domain.usecase

import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails
import com.ynov.movieappv2.tvshow_details.domain.repository.ShowDetailsRepository

class GetShowDetailsUseCase(private val repository: ShowDetailsRepository) {
    suspend operator fun invoke(showPermalink: String): ShowDetails {
        return repository.getShowDetails(showPermalink)
    }
}