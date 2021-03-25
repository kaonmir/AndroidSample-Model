package xyz.kaonmir.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.viewmodel.SoldierViewModel

class MainActivity : AppCompatActivity() {

    private val soldierViewModel: SoldierViewModel by viewModel()

    private lateinit var editTextName: EditText
    private lateinit var editTextSerialNumber: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views of components
        editTextName = findViewById(R.id.et_name)
        editTextSerialNumber = findViewById(R.id.et_serial_number)
        textViewResult = findViewById(R.id.tv_result)
        buttonSubmit = findViewById(R.id.btn_submit)

        // Set events
        setEvents()

    }

    private fun setEvents() {
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val serialNumber = editTextSerialNumber.text.toString()

            // Validation 검사 해야함

            val newSoldier = Soldier(serialNumber, name)
//            GlobalScope.launch {
//                soldiers.value?.add(newSoldier)
//                db.soldierDao().insertAll(newSoldier)
//            }
        }

//        soldiers.observe(this, Observer {
//            updateUI()
//        })
    }

//    private fun updateUI() {
//        textViewResult.text = soldiers.value?.joinToString(separator = "\n") { it.toString() }
//    }
}

// todo(learn about viewModel)