package com.example.apipractice.fake

import com.example.apipractice.domain.model.Amphibian
import com.example.apipractice.domain.repository.AmphibianRepository
import com.example.apipractice.domain.usecase.GetAmphibianUseCase
import com.example.apipractice.utils.Result
import kotlinx.coroutines.CompletableDeferred

class FakeGetAmphibianUseCase : GetAmphibianUseCase(
    amphibianRepository = FakeAmphibianRepository()
) {
    private val deferredResult = CompletableDeferred<Result<List<Amphibian>>>()

    override suspend operator fun invoke(): Result<List<Amphibian>> {
        return deferredResult.await()
    }

    fun completeWithSuccess(data: List<Amphibian> = emptyList()) {
        deferredResult.complete(value = Result.Success(data))
    }

    fun completeWithError(exception: Exception) {
        deferredResult.complete(value = Result.Error(exception))
    }
}

private class FakeAmphibianRepository : AmphibianRepository {
    override suspend fun getAmphibians(): Result<List<Amphibian>> {
        return Result.Success(data = emptyList())
    }
}

