package com.ynov.movieappv2.tvshow_details.domain.repository

import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails

interface ShowDetailsRepository {
    suspend fun getShowDetails(showPermalink: String): ShowDetails
}