package com.example.notificationapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etName = findViewById<EditText>(R.id.etName)
        var submit = findViewById<Button>(R.id.submit)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        submit.setOnClickListener {

            var name = etName.text.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                var builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("My Notification")
                    .setContentText("Hello $name !")
                notificationManager.notify(1234, builder.build())
            } else {
                 var   builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("My Notification")
                    .setContentText("Hello $name")
                notificationManager.notify(1234, builder.build())
            }

        }

    }
}