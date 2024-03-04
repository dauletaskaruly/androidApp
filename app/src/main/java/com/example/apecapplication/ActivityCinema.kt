package com.example.apecapplication
import CinemaSeatAdapter
import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
import Seat


class ActivityCinema : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)

        fun createSeatsList(): List<Seat> {
            val seatsList = mutableListOf<Seat>()
            val totalSeats = 10 // Общее количество мест
            for (i in 1..totalSeats) {
                seatsList.add(Seat(i, isOccupied = false))
            }
            return seatsList
        }
        val seatsList = createSeatsList()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerCinemaHall)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CinemaSeatAdapter(this, seatsList = seatsList)
        recyclerView.adapter = adapter


        // Получение данных о блюде из интента
//        val filmName = intent.getStringExtra("film_name")
//        val filmImg = intent.getStringExtra("film_image")
//
//        val textView = findViewById<TextView>(R.id.textViewDishName)
//        val dishImgResourceId = intent.getIntExtra("f_image", 0)
//
//        val imgView = findViewById<ImageView>(R.id.imageViewDish)
//        // Установка названия блюда
//        textView.text = dishName
//        imgView.setImageResource(dishImgResourceId)
//        val buyButton: Button = findViewById(R.id.)

        val customerNameEditText: EditText = findViewById(R.id.customerNameEditText)

        val seatView = findViewById<ImageView>(R.id.imageViewSquare)

        val dishNameTextView: TextView = findViewById(R.id.textViewDishName)

        seatView.setOnClickListener {
            val customerName: String = customerNameEditText.text.toString()
            val dishName: String = dishNameTextView.text.toString()

            val dbHelper = DatabaseHelper(this)
            dbHelper.addOrder(customerName, dishName)
            Toast.makeText(this, "Заказ добавлен в базу данных", Toast.LENGTH_SHORT).show()
        }




//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_home -> {
//                    // Открываем экран Home
//
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.navigation_orders -> {
//                    // Открываем экран Orders
//                    val intent = Intent(this, Orders::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.navigation_profile -> {
//                    // Открываем экран Profile
//                    true
//                }
//                else -> false
//            }
//        }
    }
}

