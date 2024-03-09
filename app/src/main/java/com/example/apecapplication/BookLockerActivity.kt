//import android.os.Bundle
//import android.widget.Button
//import android.widget.RadioGroup
//import androidx.appcompat.app.AppCompatActivity
//import com.example.apecapplication.R
//
//class BookLockerActivity : AppCompatActivity() {
//
//    private lateinit var dbHelper: LockerDatabaseHelper
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_book_locker)
//
//        dbHelper = LockerDatabaseHelper(this)
//        val lockerRadioGroup = findViewById<RadioGroup>()
//        val bookLockerButton = findViewById<Button>(R.id.bookLockerButton)
//        bookLockerButton.setOnClickListener {
//            val selectedLockerId = lockerRadioGroup.checkedRadioButtonId
//            val selectedLocker = findViewById<RadioButton>(selectedLockerId)
//            val lockerNumber = selectedLocker.text.toString()
//
//            val lockerNameEditText = findViewById<EditText>(resources.getIdentifier("locker${selectedLockerId}NameEditText", "id", packageName))
//            val name = lockerNameEditText.text.toString()
//
//            val rowId = dbHelper.bookLocker(lockerNumber, name)
//            if (rowId != -1L) {
//                Toast.makeText(this, "Номерок успешно забронирован!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Ошибка при бронировании номерка", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
