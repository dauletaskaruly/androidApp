package com.example.apecapplication

import DatabaseHelper
import OrderAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Orders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val dbHelper = DatabaseHelper(this)
        val orders = dbHelper.getAllOrders()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = OrderAdapter(orders)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

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