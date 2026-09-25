package com.reynanfc.cinelog.domain.repository

import com.reynanfc.cinelog.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getNowPlayingMovies(): List<Movie>}