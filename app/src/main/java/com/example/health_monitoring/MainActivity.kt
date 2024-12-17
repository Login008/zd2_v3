package com.example.health_monitoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToAdd(view: View)
    {
        val intent = Intent(this, Addition::class.java)
        startActivity(intent)
    }
    fun logOut(view: View)
    {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
    fun goToList(view: View)
    {
        val intent = Intent(this, List::class.java)
        startActivity(intent)
    }
}