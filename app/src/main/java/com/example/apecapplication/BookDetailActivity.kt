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
        val textView = findViewById<TextView>(R.id.textViewBookName)

        val amountView = findViewById<TextView>(R.id.amount)




        // Установка названия блюда
        textView.text = bookName


        val customerNameEditText: EditText = findViewById(R.id.customerNameEditTextbook)


        val dbHelper = DatabaseHelperBook(this)
        val amount = dbHelper.getAmountForBook(bookName.toString())
        val buyButton: Button = findViewById(R.id.orderBook)
        val bookNameTextView: TextView = findViewById(R.id.textViewBookName)

        amountView.text = amount.toString()
        buyButton.setOnClickListener {

            if(customerNameEditText.text.toString().trim().isNotEmpty()) {
                val customerName: String = customerNameEditText.text.toString()
                val bookName: String = bookNameTextView.text.toString()
                if(amount < 0){
                    Toast.makeText(this, "Данное блюдо закончилось", Toast.LENGTH_SHORT).show()
                }else {
                    val dbHelper = DatabaseHelperBook(this)

                    if (bookName != null) {
                        dbHelper.addOrder(customerName, bookName, amount = -1)
                        Toast.makeText(this, "Заказ добавлен в базу данных", Toast.LENGTH_SHORT).show()
                    }

                }
            }else{
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()

            }





            val customerName: String = customerNameEditText.text.toString()
            val bookName: String = bookNameTextView.text.toString()
//
            val dbHelper = DatabaseHelperBook(this)
            dbHelper.addOrder(customerName, bookName, amount = -1)
            Toast.makeText(this, "Заказ добавлен в базу данных", Toast.LENGTH_SHORT).show()
        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation2)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_order -> {
                    // Открываем экран Home

                    val intent = Intent(this, BookDetailActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_orders -> {
                    // Открываем экран Orders
                    val intent = Intent(this, OrdersBook::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_back -> {
                    // Открываем экран Profile
                    val intent = Intent(this, Library::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
