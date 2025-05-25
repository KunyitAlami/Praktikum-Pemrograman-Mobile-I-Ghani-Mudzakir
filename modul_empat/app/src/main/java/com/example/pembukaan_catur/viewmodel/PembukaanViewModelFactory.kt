package com.example.pembukaan_catur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pembukaan_catur.model.PembukaanRepository

class PembukaanViewModelFactory(
    private val repository: PembukaanRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PembukaanViewModel::class.java)) {
            return PembukaanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
