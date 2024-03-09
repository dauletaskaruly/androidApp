package com.example.apecapplication
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainMenu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page4) // Устанавливаем разметку для активности

        val wardrobe: CardView = findViewById(R.id.WardrobeLink)
        wardrobe.setOnClickListener {
            val intent = Intent(this, ActivityDish::class.java)
            startActivity(intent)
        }

        val canteen: CardView = findViewById(R.id.CanteenLink)
        canteen.setOnClickListener {
            val intent = Intent(this, ActivityDish::class.java)
            startActivity(intent)
        }

        val library: CardView = findViewById(R.id.LibraryLink)
        library.setOnClickListener {
            val intent = Intent(this, Library::class.java)
            startActivity(intent)
        }
    }
}
