package com.example.apipractice.di

import com.example.apipractice.domain.repository.AmphibianRepository
import com.example.apipractice.domain.usecase.GetAmphibianUseCase

interface AppContainer {
    val amphibianRepository: AmphibianRepository
    val getAmphibianUseCase: GetAmphibianUseCase
}