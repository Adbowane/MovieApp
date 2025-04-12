package com.ynov.movieappv2.tvshow_details.data.repository

import com.ynov.movieappv2.tvshows.data.api.ApiClient
import com.ynov.movieappv2.tvshow_details.data.dto.toShowDetails
import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails
import com.ynov.movieappv2.tvshow_details.domain.repository.ShowDetailsRepository

class ShowDetailsRepositoryImpl : ShowDetailsRepository {
    override suspend fun getShowDetails(showPermalink: String): ShowDetails {
        return ApiClient.apiService.getShowDetails(showPermalink).tvShow.toShowDetails()
    }
}