package com.example.modul_5.repository

import android.util.Log
import com.example.modul_5.data.model.Product
import com.example.modul_5.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {
    fun getProducts(): Flow<Result<List<Product>>> = flow {
        try {
            val products = RetrofitInstance.api.getProducts()
            Log.d("ProductRepository", "Success fetching products: ${products.size} items")
            emit(Result.success(products))
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products", e)
            emit(Result.failure(e))
        }
    }
}
