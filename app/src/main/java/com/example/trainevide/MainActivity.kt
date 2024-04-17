package com.example.trainevide

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
        val currentNightMode = Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.i("my_tag", "Light")} // Night mode is not active, we're using the light theme.
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.i("my_tag", "Dark")
            } // Night mode is active, we're using dark theme.
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main, SearchFragment())
                .commit()
        }

    }

}