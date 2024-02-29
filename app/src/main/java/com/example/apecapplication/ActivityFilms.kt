package com.example.apecapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R.id.recyclerFilmsDishes


class ActivityFilms : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        val recyclerView: RecyclerView = findViewById(recyclerFilmsDishes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = FilmsAdapter(this, resources.getStringArray(R.array.films_array).toList())
        recyclerView.adapter = adapter
    }
}