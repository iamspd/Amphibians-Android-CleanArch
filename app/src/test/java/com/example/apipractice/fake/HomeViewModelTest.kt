package com.example.apipractice.fake

import app.cash.turbine.test
import com.example.apipractice.rules.TestDispatcherRule
import com.example.apipractice.ui.home.HomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class HomeViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var fakeGetAmphibianUseCase: FakeGetAmphibianUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        fakeGetAmphibianUseCase = FakeGetAmphibianUseCase()
        homeViewModel = HomeViewModel(fakeGetAmphibianUseCase)
    }

    @Test
    fun homeViewModel_getAmphibians_verifyFromLoadingToSuccessState() = runTest {

        homeViewModel.homeUiState.test {
            val loadingUiState = awaitItem()
            assertEquals(true, loadingUiState.isLoading)
            assertTrue("The data list should be empty.", loadingUiState.amphibians.isEmpty())

            fakeGetAmphibianUseCase.completeWithSuccess(data = FakeDataSource.amphibianList)
            val successUiState = awaitItem()
            assertEquals("isLoading should be false.", false, successUiState.isLoading)
            assertEquals("Amphibians list contains fake data.", 2, successUiState.amphibians.size)
            assertEquals("name1", successUiState.amphibians[0].name)
            assertNull(successUiState.errorMessage)
        }
    }

    @Test
    fun homeViewModel_getAmphibians_verifyFromLoadingToErrorState() = runTest {

        homeViewModel.homeUiState.test {
            val loadingUiState = awaitItem()
            assertEquals(true, loadingUiState.isLoading)
            assertTrue("The data list should be empty.", loadingUiState.amphibians.isEmpty())

            fakeGetAmphibianUseCase.completeWithError(exception = IOException("Test exception"))
            val errorUiState = awaitItem()
            assertEquals(false, errorUiState.isLoading)
            assertTrue("List is empty!", errorUiState.amphibians.isEmpty())
            assertNotNull(errorUiState.errorMessage)
            assertEquals("Please check your internet connection.", errorUiState.errorMessage)
        }
    }
}