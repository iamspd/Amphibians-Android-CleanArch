package com.example.apipractice

import android.app.Application
import com.example.apipractice.di.AppContainer
import com.example.apipractice.di.AppContainerImpl

class AmphibianApplication : Application() {
    lateinit var
            appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl()
    }
}