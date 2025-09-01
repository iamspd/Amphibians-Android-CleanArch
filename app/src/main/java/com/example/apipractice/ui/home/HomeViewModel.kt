package com.example.apipractice.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apipractice.domain.usecase.GetAmphibianUseCase
import com.example.apipractice.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val getAmphibianUseCase: GetAmphibianUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(value = HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            val result = getAmphibianUseCase()

            when (result) {
                is Result.Success -> {
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            amphibians = result.data,
                            errorMessage = null
                        )
                    }
                }

                is Result.Error -> {
                    val errorMessage =
                        when (result.exception) {
                            is IOException -> "Please check your internet connection."
                            else -> "An unexpected error occurred."
                        }

                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = errorMessage
                        )
                    }
                }
            }
        }
    }

}