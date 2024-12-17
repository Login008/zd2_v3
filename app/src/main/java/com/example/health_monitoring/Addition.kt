package com.example.health_monitoring

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class Addition : AppCompatActivity() {
    private lateinit var highPress: EditText
    private lateinit var lowPress: EditText
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        pref = getSharedPreferences("PREF", MODE_PRIVATE)
        highPress = findViewById(R.id.highPressEdit)
        lowPress = findViewById(R.id.lowPressEdit)
    }

    fun doSomething(view: View) {
        try {
            var text = highPress.text.toString() + "/" + lowPress.text.toString()
            if (text.isNotEmpty()) {
                if (highPress.text.toString().toInt() > 0 && lowPress.text.toString().toInt() > 0) {
                    var count = pref.getInt("counter", 0)
                    pref.edit().putString("ITEM$count", text).apply()
                    count += 1
                    pref.edit().putInt("counter", count).apply()
                    val intent = Intent(this, List::class.java)
                    startActivity(intent)
                } else {
                    var snackbar = Snackbar.make(findViewById(android.R.id.content), "Вводите положительные значения",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setActionTextColor(android.graphics.Color.RED)
                    snackbar.show()
                }
            } else {
                var snackbar = Snackbar.make(findViewById(android.R.id.content), "Заполните все поля",
                    Snackbar.LENGTH_LONG)
                snackbar.setActionTextColor(android.graphics.Color.RED)
                snackbar.show()
            }
        }
        catch (e:Exception)
        {
            var snackbar = Snackbar.make(findViewById(android.R.id.content), "Вводите только целые числа",
                Snackbar.LENGTH_LONG)
            snackbar.setActionTextColor(android.graphics.Color.RED)
            snackbar.show()
        }
    }
}