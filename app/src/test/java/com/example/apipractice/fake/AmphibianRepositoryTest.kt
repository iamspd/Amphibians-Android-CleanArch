package com.example.apipractice.fake

import com.example.apipractice.data.repository.AmphibianRepositoryImpl
import com.example.apipractice.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class AmphibianRepositoryTest {

    private val fakeApiService = FakeApiService()

    @Test
    fun amphibianRepository_getAmphibians_verifySuccessState() {
        runTest {
            val repository = AmphibianRepositoryImpl(
                apiService = fakeApiService
            )
            val amphibians = (repository.getAmphibians() as Result.Success).data
            assertEquals(FakeDataSource.amphibianList[0].name, amphibians[0].name)
            assertEquals(FakeDataSource.amphibianList[0].description, amphibians[0].description)
        }
    }

    @Test
    fun amphibianRepository_getAmphibians_verifyErrorState() = runTest {
        val repository = AmphibianRepositoryImpl(
            apiService = fakeApiService
        )
        fakeApiService.setShouldThrowError(true)
        val result = repository.getAmphibians()
        assertTrue("Result should be of type Result.Error.", result is Result.Error)
    }
}