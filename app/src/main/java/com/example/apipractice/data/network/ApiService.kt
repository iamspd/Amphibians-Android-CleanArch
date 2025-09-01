package com.example.apipractice.data.network

import com.example.apipractice.data.network.dto.AmphibianDto
import retrofit2.http.GET

interface ApiService {

    @GET("amphibians")
    suspend fun getAmphibians() : List<AmphibianDto>
}