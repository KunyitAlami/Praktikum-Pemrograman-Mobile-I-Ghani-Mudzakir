package com.example.modul_satu_dadu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.modul_satu_dadu.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.buttonRollDice.setOnClickListener {
            rollDice()
        }
    }

    private fun getDiceImage(diceRoll: Int): Int {
        return when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }

    private fun rollDice() {
        val diceRoll1 = Random.nextInt(1, 7)
        val diceRoll2 = Random.nextInt(1, 7)

        binding.gambarDaduPertama.setImageResource(getDiceImage(diceRoll1))
        binding.gambarDaduKedua.setImageResource(getDiceImage(diceRoll2))

        val message = if (diceRoll1 == diceRoll2) {
            "Selamat! Anda mendapatkan dadu double!"
        } else {
            "Anda belum beruntung!"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
