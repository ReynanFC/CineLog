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
}