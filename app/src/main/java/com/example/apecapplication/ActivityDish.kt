package com.example.apecapplication
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.CustomRecyclerViewAdapter
import com.example.apecapplication.R
import com.example.apecapplication.R.id.recyclerViewDishes

class ActivityDish : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        val recyclerView: RecyclerView = findViewById(recyclerViewDishes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CustomRecyclerViewAdapter(this, resources.getStringArray(R.array.dishes_array).toList())
        recyclerView.adapter = adapter
    }
}
