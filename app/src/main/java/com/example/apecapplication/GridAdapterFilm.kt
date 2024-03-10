import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.LockerActivity
import com.example.apecapplication.R

class GridAdapterFilm(
//    private val context: Context,
    private val filmNumbers: MutableList<String>,
//    private val context: Context,
    private val dbHelper: DbHelperFilm,
    private val clickListener: FilmItemClickListener) : RecyclerView.Adapter<GridAdapterFilm.ViewHolder>() {

    private val bookedColor = Color.RED
    private val defaultColor = Color.BLACK
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = filmNumbers[position]
        holder.textView.text = number
        holder.itemView.setOnClickListener { clickListener.onFilmItemClicked(position) }
        val isBooked = dbHelper.isFilmBooked( position + 1)

        // Устанавливаем цвет текста в зависимости от статуса номерка
        if (isBooked) {
            holder.textView.setBackgroundResource(R.drawable.grid_red_dr)
        } else {
            holder.textView.setBackgroundResource(R.drawable.grid_item_background)     }
    }

    override fun getItemCount() = filmNumbers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewFilm)
    }

    fun markLockerAsBooked(position: Int) {
        // Здесь можно обновить состояние номерка в списке, чтобы отметить его как занятый
        // Например, изменить его текст на "Занят"
        this.filmNumbers[position] = "Занят " + (position + 1)
        notifyItemChanged(position)
    }
}
