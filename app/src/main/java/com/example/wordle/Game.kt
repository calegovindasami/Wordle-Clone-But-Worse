package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val userName = intent.getStringExtra("Username")

        var word = getRandomWord().uppercase()

        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val btnNewWord = findViewById<Button>(R.id.btnRetry)
        val btnLetters = arrayOf(
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5)
        )

        btnNewWord.setOnClickListener{
            word = getRandomWord().uppercase()
            for ((index, letter) in word.withIndex()) {
                btnLetters[index].text = ""
            }
        }

        val txtGuess = findViewById<EditText>(R.id.txtWord)
        var numCorrectGuesses = 0

        btnGuess.setOnClickListener {
            if (txtGuess.text.toString().uppercase() == word) {
                Toast.makeText(this, "You got the word", Toast.LENGTH_SHORT).show()
                for ((index, letter) in word.withIndex()) {
                    btnLetters[index].text = letter.toString()
                }
            }
            else
            {
                for ((index, y) in word.withIndex()) {
                    for (x in txtGuess.text.toString().uppercase()) {
                        if (x == y) {
                            btnLetters[index].text = y.uppercase()
                            numCorrectGuesses++
                        }
                    }
                }
                if (numCorrectGuesses == 0)
                {
                    Toast.makeText(this, "None of the letters you guessed are in the word", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun getRandomWord():String{
        val words = arrayOf("apple", "banana", "carat", "dance", "eager", "flair", "grape", "hazel", "igloo", "jolly", "kitty", "lemon", "mango", "noble", "olive", "piano", "queen", "rumba", "silly", "tiger", "unzip", "vital", "wedge", "xenon", "yacht", "zebra")
        val randomIndex = Random().nextInt(words.size)
        return words[randomIndex]
    }
}