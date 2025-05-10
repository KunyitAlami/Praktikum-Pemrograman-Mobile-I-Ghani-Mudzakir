package com.example.pembukaan_catur

import androidx.annotation.DrawableRes

data class TampilanPembukaan(
    val nama_pembukaan: String,
    val penjelasan_singkat: String,
    val penjelasan_dua_paragraf: String,
    @DrawableRes val imageResId: Int,
    val link: String
)
