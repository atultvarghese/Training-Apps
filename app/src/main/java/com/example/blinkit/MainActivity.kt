package com.example.blinkit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blinkit.dbconnection.UsersDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
        var rememberMe = findViewById<CheckBox>(R.id.main_remember_me_checkbox)

        registerButton.setOnClickListener(){
            var registerIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }

        var h = Handler()

        var sp : SharedPreferences = getSharedPreferences("remember_me", MODE_PRIVATE)
        var db : UsersDatabase = UsersDatabase.getDataBase(this)

        usernameText.setText(sp.getString("username", ""))
        passwordText.setText(sp.getString("password", ""))

        loginButton.setOnClickListener() {
            GlobalScope.launch {
                var user = db.usersDbCreate().checkUserExisting(usernameText.text.toString(), passwordText.text.toString())
                var editor = sp.edit()
                Log.i("login", "$user")
                if (user.isEmpty()){
                    Log.i("login", "User name doesn't exists")
                    h.post { Toast.makeText(this@MainActivity, "Invalid user name or password", Toast.LENGTH_LONG).show() }

                } else {

                    if (rememberMe.isChecked){
                        editor.putString("username", usernameText.text.toString())
                        editor.putString("password", passwordText.text.toString())
                    } else {
                        editor.remove("username")
                        editor.remove("password")
                    }
                    h.post {
                        Toast.makeText(this@MainActivity, "Welcome ${usernameText.text.toString()} ", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@MainActivity, DashBoardActivity::class.java))
                    }
                }
            }
        }

    }
}