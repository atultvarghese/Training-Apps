package com.example.cardview

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usernamefield = findViewById<EditText>(R.id.username)
        var passwordfield = findViewById<EditText>(R.id.password)
        var loginButton = findViewById<Button>(R.id.login_button)
        var checkBox = findViewById<CheckBox>(R.id.remember)

        var sp : SharedPreferences = getSharedPreferences("remember", MODE_PRIVATE)

        usernamefield.setText(sp.getString("user_name", ""))
        passwordfield.setText(sp.getString("password", ""))

        loginButton.setOnClickListener(){
            var user_name = usernamefield.text.toString()
            var password = passwordfield.text.toString()
            var editor = sp.edit()
            var intent = Intent(this, MainActivity::class.java)
            if (checkBox.isChecked) {
                editor.putString("user_name", user_name)
                editor.putString("password", password)

                startActivity(intent)
            }else {
                editor.remove("user_name")
                editor.remove("password")
                startActivity(intent)
            }
            editor.apply()
        }

    }
}