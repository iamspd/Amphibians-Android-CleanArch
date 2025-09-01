package com.example.apipractice.domain.repository

import com.example.apipractice.domain.model.Amphibian
import com.example.apipractice.utils.Result

interface AmphibianRepository {
    suspend fun getAmphibians(): Result<List<Amphibian>>
}