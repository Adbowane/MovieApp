package com.ynov.movieappv2.tvshow_details.domain.model

data class ShowDetails(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val startDate: String,
    val country: String,
    val network: String,
    val status: String
)