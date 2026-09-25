package com.reynanfc.cinelog.data.remote.dto.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @param:Json(name = "dates")
    val dates: DatesDto? = null,

    @param:Json(name = "page")
    val page: Int = 0,

    @param:Json(name = "results")
    val results: List<MovieDto> = emptyList(),

    @param:Json(name = "total_pages")
    val totalPages: Int = 0,

    @param:Json(name = "total_results")
    val totalResults: Int = 0,
)
