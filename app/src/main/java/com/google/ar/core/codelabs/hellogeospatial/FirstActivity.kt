package com.google.ar.core.codelabs.hellogeospatial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.ar.core.codelabs.hellogeospatial.helpers.TargetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val buttonAdd = findViewById<Button>(R.id.addButton)
        val nameAdd = findViewById<EditText>(R.id.name)
        val longitudeAdd = findViewById<EditText>(R.id.longitude)
        val latitudeAdd = findViewById<EditText>(R.id.latitude)
        buttonAdd.setOnClickListener{
            val namevaVal = nameAdd.text
            var longitudeVal = longitudeAdd.text.toString().toDoubleOrNull()
            val latitudeVal = latitudeAdd.text.toString().toDoubleOrNull()
            if (namevaVal.isNullOrBlank() || longitudeVal == null || latitudeVal == null) {
                Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            nameAdd.setText("")
            longitudeAdd.setText("")
            latitudeAdd.setText("")
            val target = Target(namevaVal.toString(), latitudeVal, longitudeVal)
            TargetPlaces.onAdd(this,target)
        }
        val rv: RecyclerView = findViewById(R.id.rv)
        val adapter = TargetAdapter({ t -> navigateToMain(t) }, { t -> TargetPlaces.onDelete(this,t) }, { t,f -> TargetPlaces.onUpdate(this,t,f) })
        rv.adapter = adapter
        GlobalScope.launch(Dispatchers.IO) {
            TargetDatabase.getInstance(this@FirstActivity).targetDao().getAllTargetsAsFlow()
                .collect {
                    TargetPlaces.targets = it as ArrayList<Target>
                    runOnUiThread {  adapter.list = it as ArrayList<Target> }
                }
        }

    }

    private fun navigateToMain(target: Target) {
        val intent = Intent(this, HelloGeoActivity::class.java)
        intent.putExtra(EXTRA_NAME_KEY, target.name)
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_NAME_KEY = "EXTRA_NAME_KEY"
    }
}