package com.example.pembukaan_catur

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.pembukaan_catur.model.PembukaanRepository
import com.example.pembukaan_catur.ui.theme.Pembukaan_caturTheme
import com.example.pembukaan_catur.viewmodel.PembukaanViewModel
import com.example.pembukaan_catur.viewmodel.PembukaanViewModelFactory
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private lateinit var pembukaanViewModelFactory: PembukaanViewModelFactory
    private lateinit var pembukaanViewModel: PembukaanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        pembukaanViewModelFactory = PembukaanViewModelFactory(PembukaanRepository())
        pembukaanViewModel = ViewModelProvider(this, pembukaanViewModelFactory)[PembukaanViewModel::class.java]


        setContent {
            Pembukaan_caturTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "listPembukaan"
                    ) {
                        composable("listPembukaan") {
                            // Ambil state list dari ViewModel dengan collectAsState()
                            val pembukaanList by pembukaanViewModel.pembukaanList.collectAsState()
                            val context = LocalContext.current

                            DaftarPembukaan(
                                pembukaanItems = pembukaanList,
                                navController = navController,
                                onDetailClick = { item ->
                                    pembukaanViewModel.logTombolDetail(item)  // Panggil tanpa argumen
                                    val encodedDesc = Uri.encode(item.penjelasan_dua_paragraf)
                                    navController.navigate("penjelasan/$encodedDesc/${item.imageResId}")
                                },

                                onWebsiteClick = { item ->
                                    pembukaanViewModel.logTombolExplicitIntent(item)
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                                    context.startActivity(intent)
                                }
                            )

                        }
                        composable(
                            route = "penjelasan/{desc}/{img}",
                            arguments = listOf(
                                navArgument("desc") { type = NavType.StringType },
                                navArgument("img") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val desc = backStackEntry.arguments?.getString("desc") ?: ""
                            val img = backStackEntry.arguments?.getInt("img") ?: 0

                            // Log saat berpindah ke halaman Detail
                            Log.d("PembukaanViewModel", "Navigasi ke detail dengan deskripsi: $desc")

                            tampilanDetail(img = img, nama = "Detail Pembukaan", penjelasan = desc)
                        }
                    }
                }
            }
        }
    }
}

// Composable daftar pembukaan dengan callback klik
@Composable
fun DaftarPembukaan(
    pembukaanItems: List<com.example.pembukaan_catur.model.TampilanPembukaan>,
    navController: NavHostController,
    onDetailClick: (com.example.pembukaan_catur.model.TampilanPembukaan) -> Unit,
    onWebsiteClick: (com.example.pembukaan_catur.model.TampilanPembukaan) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        items(items = pembukaanItems) { item ->
            TampilanEntitasPembukaan(
                name = item.nama_pembukaan,
                image = item.imageResId,
                url = item.link,
                description = item.penjelasan_singkat,
                penjelasan = item.penjelasan_dua_paragraf,
                navController = navController,
                onDetailClick = { onDetailClick(item) },
                onWebsiteClick = { onWebsiteClick(item) }
            )
        }
    }
}

// Perbaikan fungsi TampilanEntitasPembukaan dengan callback tombol
@Composable
fun TampilanEntitasPembukaan(
    name: String,
    image: Int,
    url: String,
    description: String,
    penjelasan: String,
    navController: NavController,
    onDetailClick: () -> Unit,
    onWebsiteClick: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                ) {
                    Button(
                        onClick = onDetailClick,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.defaultMinSize(minWidth = 1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Detail", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onWebsiteClick,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.defaultMinSize(minWidth = 1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Website", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun tampilanDetail(img: Int, nama: String, penjelasan: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = "Gambar Detail dari $nama",
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = nama,
            fontSize = 30.sp,
            fontWeight = FontWeight.W800
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = penjelasan,
            fontSize = 20.sp,
        )
    }
}
