package com.reynanfc.cinelog.data.remote.dto.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @param:Json(name = "adult")
    val adult: Boolean = false,

    @param:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @param:Json(name = "genre_ids")
    val genreIds: List<Int> = emptyList(),

    @param:Json(name = "id")
    val id: Int = 0,

    @param:Json(name = "original_language")
    val originalLanguage: String? = null,

    @param:Json(name = "original_title")
    val originalTitle: String? = null,

    @param:Json(name = "overview")
    val overview: String? = null,

    @param:Json(name = "popularity")
    val popularity: Double = 0.0,

    @param:Json(name = "poster_path")
    val posterPath: String? = null,

    @param:Json(name = "release_date")
    val releaseDate: String? = null,

    @param:Json(name = "title")
    val title: String? = null,

    @param:Json(name = "video")
    val video: Boolean = false,

    @param:Json(name = "vote_average")
    val voteAverage: Double = 0.0,

    @param:Json(name = "vote_count")
    val voteCount: Int = 0,
)
