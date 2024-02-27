package com.example.apecapplication

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


@Suppress("DEPRECATION")
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
        val buyButton: Button = findViewById(R.id.order)

        val customerNameEditText: EditText = findViewById(R.id.customerNameEditText)


        val dishNameTextView: TextView = findViewById(R.id.textViewDishName)

        buyButton.setOnClickListener {
            val customerName: String = customerNameEditText.text.toString()
            val dishName: String = dishNameTextView.text.toString()

            val dbHelper = DatabaseHelper(this)
            dbHelper.addOrder(customerName, dishName)
            Toast.makeText(this, "Заказ добавлен в базу данных", Toast.LENGTH_SHORT).show()
        }




        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Открываем экран Home

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_orders -> {
                    // Открываем экран Orders
                    val intent = Intent(this, Orders::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    // Открываем экран Profile
                    true
                }
                else -> false
            }
        }
    }
}
