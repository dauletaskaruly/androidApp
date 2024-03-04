package com.example.apecapplication

import DatabaseHelper
import DatabaseHelperBook
import android.annotation.SuppressLint
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
class BookDetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Получение данных о блюде из интента
        val bookName = intent.getStringExtra("book_name")
        val bookImg = intent.getStringExtra("book_image")

        val textView = findViewById<TextView>(R.id.textViewBookName)
        val bookImgResourceId = intent.getIntExtra("book_image", 0)

        val imgView = findViewById<ImageView>(R.id.imageViewBook)
        // Установка названия блюда
        textView.text = bookName
        imgView.setImageResource(bookImgResourceId)
        val buyButton: Button = findViewById(R.id.orderBook)
//
        val customerNameEditText: EditText = findViewById(R.id.customerNameEditTextbook)
//
        val bookNameTextView: TextView = findViewById(R.id.textViewBookName)
//
        buyButton.setOnClickListener {
            val customerName: String = customerNameEditText.text.toString()
            val bookName: String = bookNameTextView.text.toString()
//
            val dbHelper = DatabaseHelperBook(this)
            dbHelper.addOrder(customerName, bookName)
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
                    val intent = Intent(this, OrdersBook::class.java)
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
