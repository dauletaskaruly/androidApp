package com.example.apecapplication
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.CustomRecyclerViewAdapter
import com.example.apecapplication.R
import com.example.apecapplication.R.id.recyclerFilmsDishes
import com.example.apecapplication.R.id.recyclerViewDishes

class ActivityCinema : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_film)
//
        val recyclerView: RecyclerView = findViewById(recyclerFilmsDishes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = FilmAdapter(this, resources.getStringArray(R.array.films_array).toList())
        recyclerView.adapter = adapter
    }
}
