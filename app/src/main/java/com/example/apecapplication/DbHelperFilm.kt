import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelperFilm(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "film.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_FILM = "film"
        private const val COLUMN_FILM_NUMBER = "number"
        private const val COLUMN_FILM_NAME = "name"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_FILM ($COLUMN_FILM_NUMBER INTEGER PRIMARY KEY, $COLUMN_FILM_NAME TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FILM")
        onCreate(db)
    }

    fun isFilmBooked(filmNumber: Int): Boolean {
        val db = readableDatabase
        val query = "SELECT $COLUMN_FILM_NAME FROM $TABLE_FILM WHERE $COLUMN_FILM_NUMBER = ?"
        val cursor = db.rawQuery(query, arrayOf(filmNumber.toString()))
        val isBooked = cursor.moveToFirst()
        cursor.close()
        return isBooked
    }

    fun bookFilm(db: SQLiteDatabase, filmNumber: Int, name: String) {
        Log.d("DbHelperfilm", "Inserting filmNumber: $filmNumber, name: $name into database")
        val values = ContentValues().apply {
            put(COLUMN_FILM_NUMBER, filmNumber)
            put(COLUMN_FILM_NAME, name)
        }
        try {
            val result = db.insert(TABLE_FILM, null, values)
            Log.d("DbHelperLocker", "Insertion result: $result")
        } catch (e: Exception) {
            Log.e("DbHelperLocker", "Error inserting data into database: ${e.message}")
        }
        db.insert(TABLE_FILM, null, values)
    }
    @SuppressLint("Range")
    fun getFilmBookerName(filmNumber: Int): String {
        val db = readableDatabase
        val query = "SELECT $COLUMN_FILM_NAME FROM $TABLE_FILM WHERE $COLUMN_FILM_NUMBER = ?"
        val cursor = db.rawQuery(query, arrayOf(filmNumber.toString()))
        var name = ""
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(COLUMN_FILM_NAME))
        }
        cursor.close()
        return name
    }

    fun removeFilmBooking(db: SQLiteDatabase, filmNumber: Int) {
        val whereClause = "$COLUMN_FILM_NUMBER = ?"
        val whereArgs = arrayOf(filmNumber.toString())

        val result = db.delete(TABLE_FILM, whereClause, whereArgs)
        Log.d("DbHelperFilm", "Removal result: $result")
    }
}
