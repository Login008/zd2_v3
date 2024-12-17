package com.example.health_monitoring

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.collections.List

class List : AppCompatActivity()
{
    private lateinit var pref: SharedPreferences
    private lateinit var listContainer: LinearLayout
    private val items = ArrayList<String>() // Список для хранения элементов

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        pref = getSharedPreferences("PREF", MODE_PRIVATE)
        listContainer = findViewById(R.id.listContainer)
        var counter = pref.getInt("counter", 0)
        for (i in 0..< counter) {
            var item = pref.getString("ITEM$i", "")
            var value : List<String> = item.toString().split('/')
            if (value[0].toInt() >= 110 && value[0].toInt() <= 130 && value[1].toInt() >= 70 && value[1].toInt() <= 85)
                items.add(i, item.toString() + " - нормальное давление")
            else if (value[0].toInt() >= 100 && value[0].toInt() < 110 && value[1].toInt() >= 60 && value[1].toInt() < 70)
                items.add(i, item.toString() + " - нормальное пониженное давление")
            else if (value[0].toInt() < 100 && value[1].toInt() < 60)
                items.add(i, item.toString() + " - у вас гипотония")
            else if (value[0].toInt() > 130 && value[0].toInt() < 140 && value[1].toInt() > 85 && value[1].toInt() < 90)
                items.add(i, item.toString() + " - нормальное повышенное давление")
            else if (value[0].toInt() >= 140 && value[1].toInt() >= 90)
                items.add(i, item.toString() + " - у вас гипертония")
            else
                items.add(i, item.toString() + " - аномалия")
        }
        updateList()
    }

    private fun updateList()
    {
        listContainer.removeAllViews() // Очищаем контейнер перед обновлением
        for (item in items) {
            val textView = TextView(this)
            textView.text = item
            textView.setTextColor(resources.getColor(android.R.color.white))
            textView.textSize = 18f
            listContainer.addView(textView)
        }
    }
}