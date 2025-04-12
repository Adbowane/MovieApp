package com.ynov.movieappv2.tvshow_details.data.dto

import com.ynov.movieappv2.tvshow_details.domain.model.ShowDetails

data class ShowDetailsResponse(
    val tvShow: ShowDetailsDto
)

data class ShowDetailsDto(
    val id: Int,
    val name: String,
    val description: String?,
    val image_path: String?,
    val start_date: String?,
    val country: String?,
    val network: String?,
    val status: String?
)

fun ShowDetailsDto.toShowDetails(): ShowDetails {
    return ShowDetails(
        id = this.id,
        name = this.name,
        description = this.description ?: "No description available",
        imageUrl = this.image_path ?: "",
        startDate = this.start_date ?: "Unknown",
        country = this.country ?: "Unknown",
        network = this.network ?: "Unknown",
        status = this.status ?: "Unknown"
    )
}