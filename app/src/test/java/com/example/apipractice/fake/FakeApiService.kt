package com.example.apipractice.fake

import com.example.apipractice.data.network.ApiService
import com.example.apipractice.data.network.dto.AmphibianDto
import java.io.IOException

class FakeApiService : ApiService {

    private var shouldThrowError = false

    fun setShouldThrowError(value: Boolean) {
        shouldThrowError = value
    }

    override suspend fun getAmphibians(): List<AmphibianDto> {
        return if (shouldThrowError) {
            throw IOException("Test Network Error")
        } else {
            FakeDataSource.amphibianDtoList
        }
    }
}