package xyz.kaonmir.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.withTransaction
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import xyz.kaonmir.koin.model.Soldier
import xyz.kaonmir.koin.database.AppDatabase

class MainActivity : AppCompatActivity() {
    private val db: AppDatabase by inject()

    private var soldiers: MutableList<Soldier> = mutableListOf()

    private lateinit var editTextName: EditText
    private lateinit var editTextSerialNumber: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views of components
        editTextName = findViewById(R.id.editTextName)
        editTextSerialNumber = findViewById(R.id.editTextSerialNumber)
        textViewResult = findViewById(R.id.textViewResult)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        // Set events
        setEvents()

        GlobalScope.launch {
            soldiers = db.soldierDao().getAll().toMutableList()
            updateUI()
        }
        updateUI()
    }

    private fun setEvents() {
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val serialNumber = editTextSerialNumber.text.toString()

            // Validation 검사 해야함

            val newSoldier = Soldier(serialNumber, name)
            GlobalScope.launch {
                soldiers.add(newSoldier)
                db.soldierDao().insertAll(newSoldier)
                updateUI()
            }
        }
    }

    private fun updateUI() {
        textViewResult.text = soldiers.joinToString(separator = "\n") { it.toString() }
    }
}