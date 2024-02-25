package com.example.apecapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DishDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish_detail)

        // Получение данных о блюде из интента
        val dishName = intent.getStringExtra("dish_name")
        val dishImg = intent.getStringExtra("dish_image")

        val textView = findViewById<TextView>(R.id.textViewDishName)
        val dishImgResourceId = intent.getIntExtra("dish_image", 0)

        val imgView = findViewById<ImageView>(R.id.imageViewDish)
        // Установка названия блюда
        textView.text = dishName
        imgView.setImageResource(dishImgResourceId)

    }
}
