package com.example.apipractice.data.network.dto

import com.squareup.moshi.Json

/**
 * Data object class that contains response objects
 * from the API.
 */
data class AmphibianDto(
    val name: String,
    val description: String,
    @field:Json(name = "img_src")
    val imgUrl: String
)
