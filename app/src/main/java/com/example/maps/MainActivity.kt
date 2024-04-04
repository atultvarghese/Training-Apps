package com.example.maps

import AccelerometerListener
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var accelerometerListener: AccelerometerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        accelerometerListener = AccelerometerListener(this) { xAcceleration, yAcceleration, zAcceleration ->
            // Perform your functionality with accelerometer data here
            Log.i("accelerometer_data", "Acceleration - X: $xAcceleration, Y: $yAcceleration, Z: $zAcceleration")

            findViewById<TextView>(R.id.acc_x).text = xAcceleration.toString()
            findViewById<TextView>(R.id.acc_y).text = yAcceleration.toString()
            findViewById<TextView>(R.id.acc_z).text = zAcceleration.toString()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        accelerometerListener.unregister()
    }}