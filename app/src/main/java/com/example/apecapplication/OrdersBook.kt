package com.example.apecapplication

import DatabaseHelperBook
import OrderAdapterBook
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class OrdersBook : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_book)

        val dbHelper = DatabaseHelperBook(this)
        val orders = dbHelper.getAllOrders()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewBook)
        val adapter = OrderAdapterBook(orders)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_order -> {
                    // Открываем экран Home
//                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_orders -> {
                    finish()
                    // Открываем экран Orders
//                    val intent = Intent(this, OrdersBook::class.java)
//                    startActivity(intent)
//                    finish()
                    true
                }
                R.id.nav_back -> {
                    val intent = Intent(this, Library::class.java)
//                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}