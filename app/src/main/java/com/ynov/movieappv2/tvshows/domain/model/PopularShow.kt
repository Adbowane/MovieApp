package com.ynov.movieappv2.tvshows.domain.model

/**
 * Modèle de données pour une série populaire
 */
data class PopularShow(
    val id: Int,
    val name: String,
    val thumbnailUrl: String,
    val startDate: String?,
    val endDate: String?,
    val country: String?,
    val network: String?
)