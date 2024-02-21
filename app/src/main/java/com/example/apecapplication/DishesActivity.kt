// DishesActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apecapplication.R

class DishesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dishes)

        val dishes = listOf(
            Dish("Пельмени", "Вкусные пельмени с мясом", R.drawable.pizza),
            Dish("Борщ", "Традиционный украинский борщ", R.drawable.borsh),
            Dish("Пицца", "Итальянская пицца с разными начинками", R.drawable.pizza),
            Dish("Салат", "Свежий овощной салат", R.drawable.saladd),
            Dish("Спагетти", "Итальянские спагетти с соусом", R.drawable.sphagetti)
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DishAdapter(dishes)
        recyclerView.adapter = adapter
    }
}
