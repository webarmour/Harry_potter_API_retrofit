package com.example.harry_potter_and_retrofit.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.harry_potter_and_retrofit.R

class NotificationUtils(
    private val application: Application,
) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel() {

        val name = "Our Notification Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val descriptionText = "CHECK YOUR MOTHER"
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager =
            application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission", "NotificationPermission")
    fun createNotification() {

        val intent = Intent(application, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            application,
            0,
            intent,
            getCorrectFlagForPendingIntent()
        )


        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
            .setSmallIcon(R.drawable.potterpng)
            .setContentTitle("Time to check mother")
            .setContentText("Hi how your momma doin")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(application).notify(
            NOTIFICATION_ID, notification
        )

    }

    private fun getCorrectFlagForPendingIntent(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    }


    companion object {
        private const val CHANNEL_ID = "Channel_id"
        private const val NOTIFICATION_ID = 1
        private val LOCK = Any()
        private var INSTANCE: NotificationUtils? = null

        fun getInstance(application: Application): NotificationUtils {

            INSTANCE?.let { it ->
                return it
            }

            synchronized(LOCK) {

                INSTANCE?.let { it ->
                    return it
                }

                INSTANCE = NotificationUtils(application)
                return NotificationUtils(application)

            }
        }

    }


}