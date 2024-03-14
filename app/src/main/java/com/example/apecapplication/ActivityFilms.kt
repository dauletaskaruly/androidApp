package com.example.apecapplication

import DbHelperFilm
import DbHelperLocker
import FilmItemClickListener
import GridAdapter
import GridAdapterFilm
import LockerItemClickListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityFilms : AppCompatActivity(), FilmItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GridAdapterFilm
    private lateinit var dbHelper: DbHelperFilm

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_activity)

        dbHelper = DbHelperFilm(this)
        recyclerView = findViewById(R.id.recyclerViewFilm)
        recyclerView.layoutManager = GridLayoutManager(this, 6)

        // Создаем список но    мерков
        val filmNumbers = mutableListOf<String>()
        for (i in 1..60) {
            filmNumbers.add("$i")
        }

        // Создаем адаптер и устанавливаем его в RecyclerView
        adapter = GridAdapterFilm(filmNumbers, dbHelper, this)
        recyclerView.adapter = adapter
    }

    // Метод обратного вызова, вызываемый при нажатии на номерок
    override fun onFilmItemClicked(position: Int) {
        val dbHelper = DbHelperFilm(this)
        val db = dbHelper.writableDatabase


        val filmNumber = position + 1 // Потому что позиции начинаются с 0, а номерки с 1
        val isBooked = dbHelper.isFilmBooked(filmNumber)

        if (isBooked) {
            // Этот номерок уже забронирован
            val name = dbHelper.getFilmBookerName(filmNumber)
            Toast.makeText(this, "Место забронировано пользователем: $name", Toast.LENGTH_SHORT)
                .show()

            // Предложение снять бронь
            AlertDialog.Builder(this)
                .setTitle("Снять бронь?")
                .setMessage("Вы действительно хотите снять бронь с места ${filmNumber}?")
                .setPositiveButton("Да") { _, _ ->
                    dbHelper.removeFilmBooking(db, filmNumber)
                    adapter.notifyItemChanged(position)
                    Toast.makeText(this, "Бронь снята с места $filmNumber", Toast.LENGTH_SHORT)
                        .show()
                }
                .setNegativeButton("Нет", null)
                .show()
        } else {


            // Создаем диалоговое окно для ввода имени
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Забронировать место")
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
                dbHelper.bookFilm(db, filmNumber, name)

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
}
