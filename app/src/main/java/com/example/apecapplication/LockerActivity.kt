package com.example.apecapplication

import DbHelperLocker
import GridAdapter
import LockerItemClickListener
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LockerActivity : AppCompatActivity(), LockerItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GridAdapter
    private lateinit var dbHelper: DbHelperLocker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.locker_activity)
        dbHelper = DbHelperLocker(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Создаем список но    мерков
        val lockerNumbers = mutableListOf<String>()
        for (i in 1..50) {
            lockerNumbers.add("Номерок $i")
        }

        // Создаем адаптер и устанавливаем его в RecyclerView
        adapter = GridAdapter(lockerNumbers, dbHelper, this)
        recyclerView.adapter = adapter
    }

    // Метод обратного вызова, вызываемый при нажатии на номерок
    override fun onLockerItemClicked(position: Int) {
        val dbHelper = DbHelperLocker(this)
        val db = dbHelper.writableDatabase

        val lockerNumber = position + 1 // Потому что позиции начинаются с 0, а номерки с 1
        val isBooked = dbHelper.isLockerBooked(lockerNumber)

        if (isBooked) {
            // Этот номерок уже забронирован
            // Добавьте здесь логику для снятия брони
            return
        }

        // Создаем диалоговое окно для ввода имени
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Забронировать номерок")
        val input = EditText(this)
        builder.setView(input)

        // Обработка нажатия кнопки "Забронировать"
        builder.setPositiveButton("Забронировать") { dialog, _ ->
            val name = input.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Введите ваше имя", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            // Бронируем номерок в базе данных
            dbHelper.bookLocker(db, lockerNumber, name)

            // Обновляем отображение
            adapter.notifyItemChanged(position)
            dialog.dismiss()
        }

        // Обработка нажатия кнопки "Отмена"
        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()

    }
}
