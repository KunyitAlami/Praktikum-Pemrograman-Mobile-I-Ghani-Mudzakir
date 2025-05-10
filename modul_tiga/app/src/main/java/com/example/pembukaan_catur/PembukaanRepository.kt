package com.example.pembukaan_catur

class PembukaanRepository {
    fun ambilSemuaPembukaan(): List<TampilanPembukaan>{
        return listOf(
            TampilanPembukaan(
                nama_pembukaan = "Sicilian Defense",
                penjelasan_singkat = "Sebuah pembukaan agresif yang dimulai dengan e4 c5, sering digunakan untuk menciptakan permainan tidak seimbang.",
                penjelasan_dua_paragraf = """
        Sicilian Defense adalah salah satu pembukaan paling populer dan tajam dalam catur modern. Dengan membalas e4 dengan c5, hitam menghindari simetri dan berusaha mengambil inisiatif dengan permainan sayap.
        
        Pembukaan ini terkenal menghasilkan posisi yang kompleks dan penuh taktik. Banyak juara dunia, seperti Kasparov dan Fischer, mengandalkan Sicilian untuk menghadapi pemain e4.
    """.trimIndent(),
                imageResId = R.drawable.p1,
                link = "https://www.chess.com/openings/Sicilian-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "French Defense",
                penjelasan_singkat = "Strategi yang dimulai dengan e4 e6, di mana hitam memblokir pion putih dan merencanakan serangan melalui posisi yang lebih tertutup.",
                penjelasan_dua_paragraf = """
        French Defense memberikan struktur pion yang kokoh dan solid untuk hitam. Ia menawarkan banyak kemungkinan strategis, terutama dalam permainan tengah.
        
        Salah satu ciri khasnya adalah konflik antara pion e5 putih dan struktur pertahanan hitam di d5. Taktik dan strategi memainkan peran penting dalam membuka posisi ini.
    """.trimIndent(),
                imageResId = R.drawable.p2,
                link = "https://www.chess.com/openings/French-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Ruy López Opening",
                penjelasan_singkat = "Pembukaan klasik yang dimulai dengan e4 e5 2.Nf3 Nc6 3.Bb5, bertujuan untuk mengontrol pusat dan menekan pertahanan hitam.",
                penjelasan_dua_paragraf = """
        Ruy López adalah salah satu pembukaan tertua yang masih dimainkan di level tinggi. Dengan menekan kuda di c6, putih mencoba mengganggu kontrol hitam terhadap pusat.
        
        Posisi yang timbul sering bersifat strategis, dengan ruang untuk manuver jangka panjang dan potensi serangan raja di tahap akhir pembukaan.
    """.trimIndent(),
                imageResId = R.drawable.p3,
                link = "https://www.chess.com/openings/Ruy-Lopez-Opening"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Caro-Kann Defense",
                penjelasan_singkat = "Dimulai dengan e4 c6, hitam berusaha untuk memperkuat pusat dan membangun pertahanan yang solid.",
                penjelasan_dua_paragraf = """
        Caro-Kann terkenal karena stabilitas dan keamanan bagi raja hitam. Ini adalah pembukaan pilihan bagi pemain yang menyukai posisi bertahan namun aktif.
        
        Hitam membentuk d5 segera setelah c6, berusaha menetralisir pusat putih tanpa menciptakan kelemahan signifikan.
    """.trimIndent(),
                imageResId = R.drawable.p4,
                link = "https://www.chess.com/openings/Caro-Kann-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Italian Game",
                penjelasan_singkat = "Sebuah pembukaan yang sering mengarah pada permainan terbuka, dimulai dengan e4 e5 2.Nf3 Nc6 3.Bc4, dengan tujuan menyerang pusat lawan.",
                penjelasan_dua_paragraf = """
        Italian Game memberikan peluang pengembangan cepat bagi kedua pihak. Putih langsung mengincar titik lemah f7, titik lemah paling rentan bagi hitam di awal permainan.
        
        Pembukaan ini cocok untuk pemain pemula hingga master karena keseimbangan antara taktik dan strategi.
    """.trimIndent(),
                imageResId = R.drawable.p5,
                link = "https://www.chess.com/openings/Italian-Game"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Queen's Gambit",
                penjelasan_singkat = "Pembukaan populer yang dimulai dengan d4 d5 2.c4, di mana putih menawarkan pion untuk mengontrol pusat papan.",
                penjelasan_dua_paragraf = """
        Queen's Gambit adalah salah satu pembukaan tertua dan paling dihormati dalam catur. Meski disebut 'gambit', pion yang dikorbankan biasanya dapat direbut kembali.
        
        Tujuan utama putih adalah mengalihkan pion d5 hitam dan menciptakan dominasi penuh di pusat papan. Banyak juara dunia telah menggunakan pembukaan ini dengan sukses besar.
    """.trimIndent(),
                imageResId = R.drawable.p6,
                link = "https://www.chess.com/openings/Queens-Gambit"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Slav Defense",
                penjelasan_singkat = "Dimulai dengan d4 d5 2.c4 c6, hitam bertujuan untuk menjaga pusat dan bersiap untuk melawan serangan putih.",
                penjelasan_dua_paragraf = """
        Slav Defense adalah respon solid terhadap Queen’s Gambit. Dengan memainkan c6, hitam memperkuat kontrol atas d5 tanpa membuka terlalu banyak ruang.
        
        Ini menghasilkan posisi yang seimbang namun fleksibel, memungkinkan transisi ke berbagai rencana strategis tergantung perkembangan permainan.
    """.trimIndent(),
                imageResId = R.drawable.p7,
                link = "https://www.chess.com/openings/Slav-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "King's Indian Defense",
                penjelasan_singkat = "Strategi yang dimulai dengan 1.d4 Nf6 2.c4 g6, hitam berencana menyerang pusat dengan pion dan pasukan yang dikembangkan setelahnya.",
                penjelasan_dua_paragraf = """
        King's Indian Defense dikenal dengan pendekatannya yang agresif dan asimetris. Hitam mengizinkan putih membangun pusat besar, lalu menyerangnya.
        
        Strategi khasnya adalah serangan raja oleh hitam, bahkan ketika putih mengembangkan keunggulan ruang. Ini adalah pilihan ideal bagi pecatur taktis.
    """.trimIndent(),
                imageResId = R.drawable.p8,
                link = "https://www.chess.com/openings/Kings-Indian-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Nimzo-Indian Defense",
                penjelasan_singkat = "Dimulai dengan d4 Nf6 2.c4 e6 3.Nc3 Bb4, hitam bertujuan untuk mengendalikan pusat sambil mengembangkan tekanan terhadap pion putih.",
                penjelasan_dua_paragraf = """
        Nimzo-Indian Defense adalah pembukaan strategis yang memanfaatkan ancaman terhadap struktur pion putih di awal. Dengan Bb4, hitam menekan Nc3 dan menciptakan potensi kerusakan struktur.
        
        Pembukaan ini menggabungkan kontrol pusat dengan tekanan posisi dan cocok bagi pemain yang menyukai fleksibilitas.
    """.trimIndent(),
                imageResId = R.drawable.p9,
                link = "https://www.chess.com/openings/Nimzo-Indian-Defense"
            ),

            TampilanPembukaan(
                nama_pembukaan = "Queen's Indian Defense",
                penjelasan_singkat = "Dimulai dengan 1.d4 Nf6 2.c4 e6 3.Nf3 b6, hitam berusaha mengembangkan bidaknya dengan cara yang fleksibel dan mengontrol pusat.",
                penjelasan_dua_paragraf = """
        Queen’s Indian Defense fokus pada perkembangan bidak yang fleksibel dan pengendalian diagonal panjang dengan gajah di b7. Ini adalah pembukaan yang solid dan penuh manuver.
        
        Pembukaan ini cocok untuk pemain yang ingin menghindari konflik langsung di awal, namun siap melawan balik saat permainan berkembang.
    """.trimIndent(),
                imageResId = R.drawable.p10,
                link = "https://www.chess.com/openings/Queens-Indian-Defense"
            )

        )
    }
}