package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channelID"
    private val CHANNEL_NAME = "channelName"
    private val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel() // only needed to call

        // We need a pending intent to open our app when a notification  is clicked
        // To create a pending intent we need to create a normal intent first
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            // this will add the this activity to the activity backstack
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // creates the notification message
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Awesome Notification")
            .setAutoCancel(true)
            .setContentText("This is notification text.")
            .setSmallIcon(R.drawable.ic_action_name)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // how important this notification is. higher priority is schedule before
            .setContentIntent(pendingIntent)
            .build()

        // Notificationmanager
        val notificationManager = NotificationManagerCompat.from(this)

        btnShow.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    // Needed to create notification channel
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply { // The importance bepaalt extra effects
                    lightColor = Color.RED // Rood notificatie lampje
                    enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // casting by using as
            manager.createNotificationChannel(channel)

        }
    }
}