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

class CustomRecyclerViewAdapter(
    private val context: Context,
    private val dishes: List<String>
) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishNameTextView: TextView = view.findViewById(R.id.textViewDishName)
        val imageViewDish: ImageView = view.findViewById(R.id.imageViewDish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dish_with_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dishInfo = dishes[position].split("|")
        val dishName = dishInfo[0]
        val dishAmount = dishInfo[2].toInt()

        val dishImageResId = context.resources.getIdentifier(dishInfo[1], "drawable", context.packageName)

        holder.dishNameTextView.text = dishName
        holder.imageViewDish.setImageResource(dishImageResId)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra("dish_name", dishName)
            intent.putExtra("dish_amount", dishAmount)
            // Если вам также нужно передать изображение блюда, вы можете использовать:
            intent.putExtra("dish_image", dishImageResId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}
