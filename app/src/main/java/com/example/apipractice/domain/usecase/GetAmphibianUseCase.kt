package com.example.apipractice.domain.usecase

import com.example.apipractice.domain.model.Amphibian
import com.example.apipractice.domain.repository.AmphibianRepository
import com.example.apipractice.utils.Result

open class GetAmphibianUseCase(
    private val amphibianRepository: AmphibianRepository
) {
    open suspend operator fun invoke() : Result<List<Amphibian>> {
        return amphibianRepository.getAmphibians()
    }
}