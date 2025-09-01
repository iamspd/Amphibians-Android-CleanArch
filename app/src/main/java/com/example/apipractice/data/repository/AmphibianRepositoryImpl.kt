package com.example.apipractice.data.repository

import com.example.apipractice.data.network.ApiService
import com.example.apipractice.data.network.dto.AmphibianDto
import com.example.apipractice.domain.model.Amphibian
import com.example.apipractice.domain.repository.AmphibianRepository
import com.example.apipractice.utils.Result

class AmphibianRepositoryImpl(
    private val apiService: ApiService
) : AmphibianRepository {
    override suspend fun getAmphibians(): Result<List<Amphibian>> {
        return try {
            val amphibianDto = apiService.getAmphibians()
            Result.Success(data = amphibianDto.map { it.toAmphibian() })
        } catch (e: Exception) {
            Result.Error(exception = e)
        }
    }
}

private fun AmphibianDto.toAmphibian(): Amphibian {
    return Amphibian(
        name = this.name,
        description = this.description,
        imgUrl = this.imgUrl
    )
}