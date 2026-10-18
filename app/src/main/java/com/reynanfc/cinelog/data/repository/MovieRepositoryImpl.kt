package com.reynanfc.cinelog.data.repository

import com.reynanfc.cinelog.data.remote.api.MovieApiService
import com.reynanfc.cinelog.domain.model.Movie
import com.reynanfc.cinelog.domain.repository.MovieRepository
import com.reynanfc.cinelog.data.mapper.toMovie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
): MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        val response = movieApiService.getPopularMovies()
        return response.results.map { it.toMovie() }
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        val response = movieApiService.getNowPlayingMovies()
        return response.results.map { it.toMovie() }
    }

    override suspend fun searchMovies(query: String, genreId: Int?): List<Movie> {
        val trimmedQuery = query.trim()

        val response = when {
            trimmedQuery.isNotEmpty() -> movieApiService.searchMovies(query = trimmedQuery)
            genreId != null -> movieApiService.discoverMoviesByGenre(genreId = genreId)
            else -> movieApiService.getPopularMovies()
        }

        val movies = response.results.map { it.toMovie() }

        return if (trimmedQuery.isNotEmpty() && genreId != null) {
            movies.filter { movie -> genreId in movie.genreIds }
        } else {
            movies
        }
    }
}