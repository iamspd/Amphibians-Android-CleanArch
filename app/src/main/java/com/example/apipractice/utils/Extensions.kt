package com.example.apipractice.utils

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.apipractice.AmphibianApplication

fun CreationExtras.AmphibianApplication() =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibianApplication