import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.LockerActivity
import com.example.apecapplication.R

class GridAdapter(
//    private val context: Context,
    private val lockerNumbers: MutableList<String>,
//    private val context: Context,
    private val dbHelper: DbHelperLocker,
    private val clickListener: LockerItemClickListener) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private val bookedColor = Color.RED
    private val defaultColor = Color.BLACK
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = lockerNumbers[position]
        holder.textView.text = number
        holder.itemView.setOnClickListener { clickListener.onLockerItemClicked(position) }
        val isBooked = dbHelper.isLockerBooked( position + 1)

        // Устанавливаем цвет текста в зависимости от статуса номерка
        if (isBooked) {
            holder.textView.setBackgroundColor(bookedColor)
        } else {
            holder.textView.setTextColor(defaultColor)
        }
    }

    override fun getItemCount() = lockerNumbers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    fun markLockerAsBooked(position: Int) {
        // Здесь можно обновить состояние номерка в списке, чтобы отметить его как занятый
        // Например, изменить его текст на "Занят"
        this.lockerNumbers[position] = "Занят " + (position + 1)
        notifyItemChanged(position)
    }
}
