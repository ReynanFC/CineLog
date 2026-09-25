package com.reynanfc.cinelog.data.remote.dto.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatesDto(
    @param:Json(name = "maximum")
    val maximum: String? = null,

    @param:Json(name = "minimum")
    val minimum: String? = null,
)
