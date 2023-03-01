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

        // gets the username from the MainActivity
        val userName = intent.getStringExtra("Username")

        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val btnNewWord = findViewById<Button>(R.id.btnRetry)
        val txtGuess = findViewById<EditText>(R.id.txtWord)

        // an array of the button components which are used to visually show the user which letters are correct
        val btnLetters = arrayOf(
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5)
        )

        var numCorrectGuesses = 0
        var word = getRandomWord().uppercase()

        // if the user wants to get a new word
        btnNewWord.setOnClickListener{
            word = getRandomWord().uppercase()
            for ((index) in word.withIndex()) {
                btnLetters[index].text = ""
            }
        }

        // Takes in the user input to see if they got the word
        btnGuess.setOnClickListener {
            // if the word is correct
            if (txtGuess.text.toString().uppercase() == word) {
                Toast.makeText(this, "You got the word $userName", Toast.LENGTH_SHORT).show()
                for ((index, letter) in word.withIndex()) {
                    btnLetters[index].text = letter.toString()
                }
            }
            else
            {
                // checks to see if the incorrect guess has any of the same letters and displays it in its correct position to the user
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