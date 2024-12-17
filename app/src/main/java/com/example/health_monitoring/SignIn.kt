package com.example.health_monitoring

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class SignIn : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var pass: EditText
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        login = findViewById(R.id.login)
        pass = findViewById(R.id.pass)
        pref = getSharedPreferences("PREF", MODE_PRIVATE)
    }

    fun next(view: View)
    {
        if (pref.getBoolean("IsAuthorized", false))
        {
            //auth
            if(!login.text.toString().isNullOrEmpty() && !pass.text.toString().isNullOrEmpty())
            {
                if (login.text.toString() == (pref.getString("login","")) && pass.text.toString() ==
                    (pref.getString("password","")))
                {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    var snackbar = Snackbar.make(findViewById(android.R.id.content),"Неверный логин или пароль",
                        Snackbar.LENGTH_LONG)
                    snackbar.setActionTextColor(android.graphics.Color.RED)
                    snackbar.show()
                }

            }
            else
            {
                var snackbar = Snackbar.make(findViewById(android.R.id.content),"Вы не заполнили логин или пароль",
                    Snackbar.LENGTH_LONG)
                snackbar.setActionTextColor(android.graphics.Color.RED)
                snackbar.show()
            }
        }
        else
        {
            //reg
            if (!login.text.isNullOrEmpty() && !pass.text.isNullOrEmpty())
            {
                pref.edit().putBoolean("IsAuthorized", true).apply()
                pref.edit().putString("login", login.text.toString()).apply()
                pref.edit().putString("password", pass.text.toString()).apply()

                val snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Вы теперь зарегистрированы в системе", Snackbar.LENGTH_LONG)
                snackbar.setActionTextColor(android.graphics.Color.RED)
                snackbar.show()
            }
            else
            {
                val snackbar = Snackbar.make(view, "Логин или пароль не заполнены", Snackbar.LENGTH_LONG)
                snackbar.setActionTextColor(android.graphics.Color.RED)
                snackbar.show()
            }
        }
    }
}