package com.example.apecapplication

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmsAdapter(private val films: ActivityFilms, private val onItemClick: List<String>) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmNameTextView: TextView = itemView.findViewById(R.id.textViewFilmName)
        val imageViewFilm: ImageView = itemView.findViewById(R.id.imageViewFilm)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick.invoke(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film_with_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmName = films[position]
        holder.filmNameTextView.text = filmName
        holder.filmNameTextView.text = filmName
        // Здесь можно установить изображение фильма
    }

    override fun getItemCount(): Int {
        return films.size
    }
}
