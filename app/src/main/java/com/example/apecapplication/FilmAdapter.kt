package com.example.apecapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R

class FilmAdapter  (
    private val context: Context,
    private val films: List<String>
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val filmNameTextView: TextView = view.findViewById(R.id.textViewFilmName)
        val imageViewFilm: ImageView = view.findViewById(R.id.imageViewFilm)
        val textViewFilmTime: TextView = view.findViewById(R.id.textViewFilmTime)
        val textViewFilmAbout: TextView = view.findViewById(R.id.textViewFilmAbout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_film_with_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmInfo = films[position].split("|")
        val filmName = filmInfo[0]
        val filmImageResId = context.resources.getIdentifier(filmInfo[1], "drawable", context.packageName)
        val filmTime = filmInfo[2]
        val filmAbout = filmInfo[3]

        holder.filmNameTextView.text = filmName
        holder.imageViewFilm.setImageResource(filmImageResId)
        holder.textViewFilmTime.text = filmTime
        holder.textViewFilmAbout.text = filmAbout
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ActivityFilms::class.java)
            intent.putExtra("film_name", filmName)
            intent.putExtra("film_image", filmImageResId)
            intent.putExtra("film_time", filmTime)
            intent.putExtra("film_about", filmAbout)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return films.size
    }

}

