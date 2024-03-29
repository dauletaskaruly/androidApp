package com.example.apecapplication
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page4) // Устанавливаем разметку для активности

        // Получение переданного имени из Intent
        val username = intent.getStringExtra("USERNAME_KEY")

        // Используйте значение имени в вашем коде, например, установите его в TextView
        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = "$username"

        val wardrobe: CardView = findViewById(R.id.WardrobeLink)
        wardrobe.setOnClickListener {
            val intent = Intent(this, LockerActivity::class.java)
            startActivity(intent)
        }
        val library: CardView = findViewById(R.id.LibraryLink)
        library.setOnClickListener {
            val intent = Intent(this, Library::class.java)
            startActivity(intent)
        }

        val canteen: CardView = findViewById(R.id.CanteenLink)
        canteen.setOnClickListener {
            val intent = Intent(this, ActivityDish::class.java)
            startActivity(intent)
        }

        val cinema: CardView = findViewById(R.id.CinemaLink)
        cinema.setOnClickListener {
            val intent = Intent(this, ActivityCinema::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Открываем экран Home

                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.navigation_orders -> {
                    // Открываем экран AboutUs
                    val intent = Intent(this, AboutUs::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.navigation_profile -> {
                    // Открываем экран Login
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
