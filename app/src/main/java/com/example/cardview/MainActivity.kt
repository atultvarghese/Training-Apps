package com.example.cardview

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
//        Notification
        val CHANNEL_ID = "Information"
        val CHANNEL_NAME = "Information Channel"

        fun createNotificationChannel(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val existingChannel = notificationManager.getNotificationChannel(CHANNEL_ID)
                if (existingChannel == null){
                    val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                        description = "Notification descriptionText"
                    }
                    notificationManager.createNotificationChannel(channel)
                }
            }
        }

        fun showNotification(){
            var intent = Intent(this, SplashScreenActivity::class.java)
            var pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Atuls Title")
                .setContentText("This is my notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build()

            val notificationManager = NotificationManagerCompat.from(this)
            if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED){
                return
            }
            notificationManager.notify(0,notification)
        }

        createNotificationChannel()
        showNotification()

    }
}