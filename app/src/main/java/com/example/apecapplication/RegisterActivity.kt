package com.example.apecapplication

import DbHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DbHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        dbHelper = DbHelper(this)
        val signButton = findViewById<Button>(R.id.login_link_btn)
        signButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Обработка нажатия кнопки регистрации
        val registerButton = findViewById<Button>(R.id.register_btn)
        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_reg).text.toString()
            val password = findViewById<EditText>(R.id.reg_password ).text.toString()
            val name = findViewById<EditText>(R.id.username_reg).text.toString()

            // Добавление пользователя в базу данных
            val userId = dbHelper.addUser(email, password, name)

            if (userId != -1L) {
                // Пользователь успешно зарегистрирован
                Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ActivityDish::class.java)
                startActivity(intent)
                finish()
            } else {
                // Произошла ошибка при регистрации
                Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
