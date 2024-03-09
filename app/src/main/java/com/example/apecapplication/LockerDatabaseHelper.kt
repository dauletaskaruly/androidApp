import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelperLocker(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "locker.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_LOCKERS = "lockers"
        private const val COLUMN_LOCKER_NUMBER = "number"
        private const val COLUMN_LOCKER_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_LOCKERS ($COLUMN_LOCKER_NUMBER INTEGER PRIMARY KEY, $COLUMN_LOCKER_NAME TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_LOCKERS")
        onCreate(db)
    }

    fun isLockerBooked(lockerNumber: Int): Boolean {
        val db = readableDatabase
        val query = "SELECT $COLUMN_LOCKER_NAME FROM $TABLE_LOCKERS WHERE $COLUMN_LOCKER_NUMBER = ?"
        val cursor = db.rawQuery(query, arrayOf(lockerNumber.toString()))
        val isBooked = cursor.moveToFirst()
        cursor.close()
        return isBooked
    }

    fun bookLocker(db: SQLiteDatabase, lockerNumber: Int, name: String) {
        Log.d("DbHelperLocker", "Inserting lockerNumber: $lockerNumber, name: $name into database")
        val values = ContentValues().apply {
            put(COLUMN_LOCKER_NUMBER, lockerNumber)
            put(COLUMN_LOCKER_NAME, name)
        }
        try {
            val result = db.insert(TABLE_LOCKERS, null, values)
            Log.d("DbHelperLocker", "Insertion result: $result")
        } catch (e: Exception) {
            Log.e("DbHelperLocker", "Error inserting data into database: ${e.message}")
        }
        db.insert(TABLE_LOCKERS, null, values)
    }
}
