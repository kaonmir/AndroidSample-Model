package xyz.kaonmir.model

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import xyz.kaonmir.model.model.Name
import xyz.kaonmir.model.model.Soldier
import xyz.kaonmir.model.viewmodel.SoldierViewModel
import java.util.regex.Pattern

@KoinApiExtension
class MainActivity : AppCompatActivity() {
    private lateinit var soldierViewModel: SoldierViewModel
    private lateinit var soldiers: LiveData<List<Soldier>>

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

            // Validation 검사 해야함
            val nameRegexMil = " *([a-zA-Z]+) *, *([a-zA-Z ]+) *(?:\\. *([a-zA-Z]+))? *" // military style

            // todo(civilian style)
//            val nameRegex = "([a-zA-Z]),([a-zA-Z])?(?:\\.([a-zA-Z]))"

            val matcher = Pattern.compile(nameRegexMil).matcher(name)
            if (matcher.matches()) {
                val newName = Name(matcher.group(2)!!, matcher.group(3), matcher.group(1)!!)
                soldierViewModel.insert(Soldier(serialNumber, newName))
            } else {
                Toast.makeText(applicationContext, "Name", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateUI() {
        textViewResult.text = soldiers.value?.joinToString(separator = "\n") { it.toString() }
    }

}

// todo(learn about viewModel)