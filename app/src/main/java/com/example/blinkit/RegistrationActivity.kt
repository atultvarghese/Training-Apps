package com.example.blinkit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.blinkit.dbconnection.UsersDatabase
import com.example.blinkit.dbconnection.UsersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.concurrent.thread

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usernameText = findViewById<EditText>(R.id.regi_user)
        var passwordText = findViewById<EditText>(R.id.regi_pass)
        var registerButton = findViewById<Button>(R.id.regi_button)

        var db = Room.databaseBuilder(this, UsersDatabase::class.java, "users")
            .fallbackToDestructiveMigration().build()

        var h = Handler()


        registerButton.setOnClickListener(){

            GlobalScope.launch {
                var users = UsersEntity()
                users.user_name = usernameText.text.toString()
                users.password = passwordText.text.toString()
                try {
                    val newRowId = db.usersDbCreate().addData(users)
                    if (newRowId.toInt() != -1){
                        withContext(Dispatchers.Main) {
                            showToast( "Registered Successfully")
                            startActivity(Intent(this@RegistrationActivity, DashBoard::class.java))
                        }
                    }else {
                        withContext(Dispatchers.Main) {
                            showToast( "User Name Already exists")
                        }
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        showToast( "Error Registering $e")
                    }

                }
            }
        }

        registerButton.setOnLongClickListener(){
            thread {
                db.usersDbCreate().getAllData().forEach {
                    h.post {
                        var result = "User Name : ${it.user_name} with password ${it.password}"
                        Thread.sleep(1000)
                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            true
        }
    }

    fun showToast(message : String){
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }
}