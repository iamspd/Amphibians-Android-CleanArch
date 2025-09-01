package com.example.apipractice.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.apipractice.ui.home.HomeViewModel
import com.example.apipractice.utils.AmphibianApplication

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            HomeViewModel(getAmphibianUseCase = AmphibianApplication().appContainer.getAmphibianUseCase)
        }
    }
}