package com.example.apecapplication
import DbHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apecapplication.MainActivity
import com.example.apecapplication.R

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DbHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        dbHelper = DbHelper(this)

        val signButton = findViewById<Button>(R.id.sign_button)
        signButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        val loginButton = findViewById<Button>(R.id.login_btn)
        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()

            val user = dbHelper.authenticateUser(username, password)

            if(username == "" || password == "")
                Toast.makeText(this, "Пользователь $username не найден", Toast.LENGTH_LONG).show()
            else{
                val db = dbHelper.authenticateUser(username, password)
                if(db) {
                    Toast.makeText(this, "Пользователь $username авторизован", Toast.LENGTH_LONG).show()

                    // Передача имени пользователя на следующую активность
                    val intent = Intent(this, MainMenu::class.java)
                    intent.putExtra("USERNAME_KEY", username)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Пользователь $username НЕ авторизован", Toast.LENGTH_LONG).show()
                }
            }
//            if (user != null) {
//                // Успешная аутентификация
//                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
//
//                // Перенаправление на главную страницу (MainActivity)
//                val intent = Intent(this, ActivityDish::class.java)
//                startActivity(intent)
//                finish() // Закрытие текущей активити
//            } else {
//                // Ошибка при аутентификации
//                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
//            }
        }
    }}
