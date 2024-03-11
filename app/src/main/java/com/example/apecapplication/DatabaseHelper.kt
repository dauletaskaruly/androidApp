import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apecapplication.Order
import java.time.temporal.TemporalAmount

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_ORDERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMER_NAME TEXT," +
                "$COLUMN_DISH_NAME TEXT," +
                "$COLUMN_IMAGE INTEGER," +
                "$COLUMN_AMOUNT INTEGER)"

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    fun addOrder(customerName: String, dishName: String, image: Int, amount: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_CUSTOMER_NAME, customerName)
        values.put(COLUMN_DISH_NAME, dishName)
        values.put(COLUMN_IMAGE, image)
        values.put(COLUMN_AMOUNT, amount)
        return db.insert(TABLE_ORDERS, null, values)
    }
    @SuppressLint("Range")
    fun getAmountForDish(dishName: String): Int {
        val db = this.readableDatabase
        var amount = 20
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ORDERS WHERE $COLUMN_DISH_NAME = ?", arrayOf(dishName))
        cursor.use {
            while (it.moveToNext()) {
                val dishAmount = it.getInt(it.getColumnIndex(COLUMN_AMOUNT))
                amount += dishAmount
            }
        }
        return amount
    }

    @SuppressLint("Range")
    fun getAllOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ORDERS", null)
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndex(COLUMN_ID))
                val customerName = it.getString(it.getColumnIndex(COLUMN_CUSTOMER_NAME))
                val dishName = it.getString(it.getColumnIndex(COLUMN_DISH_NAME))
                val image = it.getInt(it.getColumnIndex(COLUMN_IMAGE))
                val amount = it.getInt(it.getColumnIndex(COLUMN_AMOUNT))
                orders.add(Order(id, customerName, dishName, image, amount))
            }
        }
        return orders
    }
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "OrdersDB"
        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_ID = "id"
        private const val COLUMN_CUSTOMER_NAME = "customer_name"
        private const val COLUMN_DISH_NAME = "dish_name"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_AMOUNT = "amount"
    }


}
