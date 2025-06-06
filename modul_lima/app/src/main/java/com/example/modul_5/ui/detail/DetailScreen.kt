package com.example.modul_5.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.modul_5.data.model.Product
import com.example.modul_5.ui.home.HomeViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(productId: Int, viewModel: HomeViewModel) {
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val product = products.find { it.id == productId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                error != null -> {
                    Text(
                        text = error ?: "Terjadi kesalahan",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                product != null -> {
                    ProductDetailContent(product = product)
                }
                else -> {
                    Text("Produk tidak ditemukan.")
                }
            }
        }
    }
}

@Composable
fun ProductDetailContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Nama: ${product.title}", style = MaterialTheme.typography.titleMedium)
        Text("Harga: $${product.price}", style = MaterialTheme.typography.bodyMedium)
        Text("Kategori: ${product.category}", style = MaterialTheme.typography.bodyMedium)
        Text("Deskripsi: ${product.description}", style = MaterialTheme.typography.bodySmall)
    }
}

