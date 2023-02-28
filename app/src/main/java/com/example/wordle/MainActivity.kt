package com.example.wordle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSubmitName = findViewById<Button>(R.id.btnSubmitName)
        val txtName = findViewById<EditText>(R.id.txtName)

        btnSubmitName.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            intent.putExtra("Username",txtName.text)
            startActivity(intent)
        }
    }
}