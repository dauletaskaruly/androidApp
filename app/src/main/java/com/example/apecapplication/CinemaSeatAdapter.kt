
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R

class CinemaSeatAdapter(
    private val context: Context,
    private val seatsList: List<Seat>
) : RecyclerView.Adapter<CinemaSeatAdapter.SquareViewHolder>() {

    class SquareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewSquare: ImageView = itemView.findViewById(R.id.imageViewSeat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cinema_seat, parent, false)
        return SquareViewHolder(view)
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        // Установите изображение для квадрата в зависимости от состояния занятости места
//        val imageResource = R.drawable.taken_seat
        val seat = seatsList[position]
        val imageResource = if (seat.isOccupied) {
            R.drawable.taken_seat // Место занято
        } else {
            R.drawable.empty_seat // Место свободно
        }
        holder.imageViewSquare.setImageResource(imageResource)
    }

    override fun getItemCount(): Int {

        return seatsList.size // Возвращаем количество элементов
    }
    }



