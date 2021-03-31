package xyz.kaonmir.model.presentation.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.kaonmir.model.R
import xyz.kaonmir.model.data.entities.SoldierModel
import xyz.kaonmir.model.domain.exceptions.SoldierExistsException
import xyz.kaonmir.model.domain.exceptions.SoldierValidationException
import xyz.kaonmir.model.presentation.viewmodel.SoldierViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var soldierViewModel: SoldierViewModel
    private lateinit var soldiers: LiveData<List<SoldierModel>>

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

        val factory = SoldierViewModel.Companion.Factory(application)
        soldierViewModel = ViewModelProvider(viewModelStore, factory)
            .get(SoldierViewModel::class.java)
        soldiers = soldierViewModel.soldiers

        // Set events
        setEvents()
    }

    private fun setEvents() {
        soldiers.observe(this, Observer {
            if (it == null || it.isEmpty()) return@Observer
            updateUI()
        })
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val serialNumber = editTextSerialNumber.text.toString()

            try {
                soldierViewModel.insert(name, serialNumber)
            } catch (e: Exception) {
                when(e) {
                    is SoldierExistsException ->
                        Toast.makeText(applicationContext, R.string.soldierExistsException, Toast.LENGTH_SHORT).show()
                    is SoldierValidationException ->
                        Toast.makeText(applicationContext, R.string.soldierValidationException, Toast.LENGTH_SHORT).show()
                    else ->
                        Toast.makeText(applicationContext, R.string.defaultException, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUI() {
        textViewResult.text = soldiers.value?.joinToString(separator = "\n")
    }
}