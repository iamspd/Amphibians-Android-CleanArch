package com.example.apipractice.di

import com.example.apipractice.data.network.ApiService
import com.example.apipractice.data.repository.AmphibianRepositoryImpl
import com.example.apipractice.domain.repository.AmphibianRepository
import com.example.apipractice.domain.usecase.GetAmphibianUseCase
import com.example.apipractice.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainerImpl : AppContainer {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val amphibianRepository: AmphibianRepository by lazy {
        AmphibianRepositoryImpl(apiService = retrofitService)
    }

    override val getAmphibianUseCase: GetAmphibianUseCase by lazy {
        GetAmphibianUseCase(amphibianRepository = amphibianRepository)
    }
}