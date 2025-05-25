package com.example.pembukaan_catur.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pembukaan_catur.model.TampilanPembukaan
import com.example.pembukaan_catur.model.PembukaanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.util.Log

class PembukaanViewModel(private val repository: PembukaanRepository) : ViewModel() {

    private val _pembukaanList = MutableStateFlow<List<TampilanPembukaan>>(emptyList())
    val pembukaanList: StateFlow<List<TampilanPembukaan>> = _pembukaanList

    private val _selectedPembukaan = MutableStateFlow<TampilanPembukaan?>(null)
    val selectedPembukaan: StateFlow<TampilanPembukaan?> = _selectedPembukaan

    init {
        val data = repository.ambilSemuaPembukaan()
        _pembukaanList.value = data

        Log.d("PembukaanViewModel", "Data item dimuat ke dalam list: ${data.size} items")
    }

    fun pilihPembukaan(pembukaan: TampilanPembukaan) {
        _selectedPembukaan.value = pembukaan
        Log.d("PembukaanViewModel", "Item dipilih untuk detail: ${pembukaan.nama_pembukaan}")
    }

    fun logTombolDetail(item: TampilanPembukaan) {
        Log.d("PembukaanViewModel", "Tombol Detail ditekan: ${item.nama_pembukaan}")
    }

    fun logTombolExplicitIntent(item: TampilanPembukaan) {
        Log.d("PembukaanViewModel", "Tombol Website ditekan: ${item.nama_pembukaan}")
    }
}
