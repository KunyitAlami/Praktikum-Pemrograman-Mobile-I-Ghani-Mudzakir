package com.example.modul_5.data.remote

import com.example.modul_5.data.model.Product
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}
