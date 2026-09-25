package com.reynanfc.cinelog.data.remote.api

import com.reynanfc.cinelog.data.remote.dto.movie.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR"
    ): MovieResponseDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR"
    ): MovieResponseDto
}
