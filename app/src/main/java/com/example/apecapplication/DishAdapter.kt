// DishAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R

class DishAdapter(private val dishes: List<Dish>) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishImageView: ImageView = itemView.findViewById(R.id.dishImageView)
        val dishNameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        val dishDescriptionTextView: TextView = itemView.findViewById(R.id.dishDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val currentItem = dishes[position]
        holder.dishImageView.setImageResource(currentItem.imageResource)
        holder.dishNameTextView.text = currentItem.name
        holder.dishDescriptionTextView.text = currentItem.description
    }

    override fun getItemCount() = dishes.size

}
