package com.example.blinkit

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var splashScreenText = findViewById<TextView>(R.id.textViewSlashScreen)

        GlobalScope.launch {
            for (i in 1..5){
                delay(1000)
                launch(Dispatchers.Main) {
                    splashScreenText.text = "Get it in ${i.toString()} secs"
                }
            }
        }

        thread {
            Thread.sleep(5000)
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        // Set max progress of the progress bar
        progressBar.max = 100

        fun startProgressBarAnimation() {
            // Create a ValueAnimator for animating the progress
            val animator = ValueAnimator.ofInt(0, 100).apply {
                duration = 5000 // 5000 milliseconds = 5 seconds
                addUpdateListener { valueAnimator ->
                    progressBar.progress = valueAnimator.animatedValue as Int
                }
            }
            // Start the animator
            animator.start()
        }
        startProgressBarAnimation()
    }
}