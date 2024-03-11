package com.example.apecapplication
import DbHelperLocker
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LockersList : AppCompatActivity() {

    private lateinit var lockerNumberTextView: TextView


    private lateinit var dbHelper: DbHelperLocker

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lockers_list)
        val name = intent.getStringExtra("name")

        var lockerNumberTextView = findViewById<TextView>(R.id.lockerNumberTextView)
        var lockerNameTextView = findViewById<TextView>(R.id.lockerNameTextView)

        dbHelper = DbHelperLocker(this)

        // Получаем данные из базы данных
        val lockerNumber = dbHelper.getColumnLockerNumber(name)


        // Отображаем данные
        lockerNumberTextView.text = "Ваш Номерок: $lockerNumber"
        lockerNameTextView.text = "Имя: $name"
    }
}
