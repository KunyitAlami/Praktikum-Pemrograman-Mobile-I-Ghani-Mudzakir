package com.example.pembukaan_catur

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pembukaan_catur.ui.theme.Pembukaan_caturTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pembukaan_caturTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "PembukaanRepository"
                    ) {
                        composable("PembukaanRepository") {
                            DaftarPembukaan(navController)
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
                            tampilanDetail(img = img, nama = "Detail Pembukaan", penjelasan = desc)
                        }
                    }
                }
            }
        }
    }
}



// tampilan per entitas
@Composable
fun TampilanEntitasPembukaan(name: String, image: Int, url: String, description: String, penjelasan: String,  navController: NavController) {
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
                        onClick = {
                            val encodedDesc = Uri.encode(penjelasan)
                            navController.navigate("penjelasan/$encodedDesc/$image")
                        },
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.defaultMinSize(minWidth = 1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Detail", fontSize = 14.sp, )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
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

// tampilan looping untuk lazycolumn
@Composable
fun DaftarPembukaan(navController: NavHostController) {

    val repositoriPembukaan = PembukaanRepository()
    val fetchedPembukaan = repositoriPembukaan.ambilSemuaPembukaan()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        items(items=fetchedPembukaan) {
            pembukaan -> TampilanEntitasPembukaan(
                name = pembukaan.nama_pembukaan,
                image = pembukaan.imageResId,
                url = pembukaan.link,
                description = pembukaan.penjelasan_singkat,
                penjelasan = pembukaan.penjelasan_dua_paragraf,
                navController = navController
            )
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pembukaan_caturTheme {
        tampilanDetail(R.drawable.p1, "Sisilia Defense", """
        Sicilian Defense adalah salah satu pembukaan paling populer dan tajam dalam catur modern. Dengan membalas e4 dengan c5, hitam menghindari simetri dan berusaha mengambil inisiatif dengan permainan sayap.
        
        Pembukaan ini terkenal menghasilkan posisi yang kompleks dan penuh taktik. Banyak juara dunia, seperti Kasparov dan Fischer, mengandalkan Sicilian untuk menghadapi pemain e4.
    """.trimIndent())
    }
}
