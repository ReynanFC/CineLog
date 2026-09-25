package com.reynanfc.cinelog.data.mapper

import com.reynanfc.cinelog.data.remote.dto.movie.MovieDto
import com.reynanfc.cinelog.domain.model.Movie
import com.reynanfc.cinelog.util.TMDB_IMAGE_BASE_URL
import kotlin.math.roundToInt

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title ?: originalTitle ?: "Sem título",
        overview = overview.orEmpty(),
        posterUrl = posterPath?.let { path -> "${TMDB_IMAGE_BASE_URL}$path" },
        backdropUrl = backdropPath?.let { path -> "${TMDB_IMAGE_BASE_URL}$path" },
        releaseDate = releaseDate.orEmpty(),
        voteAverage = (voteAverage * 10).roundToInt() / 10.0
    )
}