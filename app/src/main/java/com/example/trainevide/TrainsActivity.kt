package com.example.trainevide

import APIClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException

class TrainsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trains)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var trainName = intent.extras!!.getString("trainName")
        var trainNumber = intent.extras!!.getString("trainNumber")
        var search = ""
        if (!trainName.isNullOrEmpty()){
            search = trainName
        } else if (!trainNumber.isNullOrEmpty()){
            search = trainNumber
        } else{
            Toast.makeText(this, "Both the fields are empty check your inputs", Toast.LENGTH_LONG).show()
            finish()
        }

        val apiClient = APIClient.create()

        apiClient.search(search, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
//                println(response.body?.string())
                Log.i("my_tagi", "${response.body?.string()}")
            }
        })

    }
}