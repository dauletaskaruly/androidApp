package com.example.apecapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R

class BookAdapter(
    private val context: Context,
    private val book: List<String>
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textBook: TextView = view.findViewById(R.id.textViewBookName)
        val imageBook: ImageView = view.findViewById(R.id.imageViewBook)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_with_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookInfo = book[position].split("|")
        val bookName = bookInfo[0]
        val bookImageResId = context.resources.getIdentifier(bookInfo[1], "drawable", context.packageName)

        holder.textBook.text = bookName
        holder.imageBook.setImageResource(bookImageResId)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("book_name", bookName)
//            Если вам также нужно передать изображение блюда, вы можете использовать:
            intent.putExtra("book_image", bookImageResId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return book.size
    }
}
