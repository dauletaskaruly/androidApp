package com.example.apecapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page4) // Устанавливаем разметку для активности

        val canteen: EditText = findViewById(R.id.whitebox1)
        canteen.setOnClickListener {
            val intent = Intent(this, ActivityDish::class.java)
            finish()
            startActivity(intent)
        }

//        val film: EditText = findViewById(R.id.whitebox2)
//        film.setOnClickListener {
//            val intent = Intent(this, ActivityDish::class.java)
//            startActivity(intent)
//        }
    }
}