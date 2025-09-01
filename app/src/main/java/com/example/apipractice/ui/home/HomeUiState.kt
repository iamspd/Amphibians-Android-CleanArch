package com.example.apipractice.ui.home

import com.example.apipractice.domain.model.Amphibian

data class HomeUiState(
    val isLoading: Boolean = true,
    val amphibians: List<Amphibian> = emptyList(),
    val errorMessage: String? = null
)