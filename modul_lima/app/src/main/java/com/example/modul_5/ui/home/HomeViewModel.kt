package com.example.modul_5.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul_5.data.model.Product
import com.example.modul_5.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                if (result.isSuccess) {
                    _products.value = result.getOrNull() ?: emptyList()
                    _error.value = null
                } else {
                    _products.value = emptyList()
                    _error.value = "Gagal memuat data produk"
                }
                _isLoading.value = false
            }
        }
    }
}
