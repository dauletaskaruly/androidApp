package com.example.apecapplication

import DishesActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Устанавливаем разметку для активности

        val button: Button = findViewById(R.id.startbutton)
        button.setOnClickListener {
            val intent = Intent(this, DishesActivity::class.java)
            startActivity(intent)
        }



    }
}
