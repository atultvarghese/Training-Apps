package com.example.blinkit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usernameText = findViewById<EditText>(R.id.main_username)
        var passwordText = findViewById<EditText>(R.id.main_password)
        var loginButton = findViewById<Button>(R.id.main_login)
        var registerButton = findViewById<Button>(R.id.main_register)

        registerButton.setOnClickListener(){
            var registerIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }

    }
}