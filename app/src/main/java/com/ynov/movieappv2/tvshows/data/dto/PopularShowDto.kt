package com.ynov.movieappv2.tvshows.data.dto

import com.ynov.movieappv2.tvshows.domain.model.PopularShow

data class PopularShowsResponse(
    val total: Int,
    val page: Int,
    val tv_shows: List<PopularShowDto>
)

data class PopularShowDto(
    val id: Int,
    val name: String,
    val permalink: String,
    val start_date: String?,
    val end_date: String?, // 👈 ici !
    val country: String?,
    val network: String?,
    val status: String?,
    val image_thumbnail_path: String
)

fun PopularShowDto.toPopularShow(): PopularShow {
    return PopularShow(
        id = id,
        name = name,
        thumbnailUrl = image_thumbnail_path,
        startDate = start_date,
        endDate = end_date,
        country = country,
        network = network
    )
}
