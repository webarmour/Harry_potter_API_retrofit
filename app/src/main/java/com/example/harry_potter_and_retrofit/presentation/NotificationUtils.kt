package com.example.harry_potter_and_retrofit.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.R

class NotificationUtils(
    private val application: Application,
) {


    fun createNotificationChannel() {
        if (checkIfBuildVersionMoreThanO()) {
            val name = "Our Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val descriptionText = "test text"
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

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
            .setBadgeIconType(R.drawable.potterpng)
            .setContentTitle("Привет")
            .setContentText("Чекни мать")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val isGrantedNotification =
            ((application as App).permissionsService.checkPermissionForNotification())
        if (isGrantedNotification != null) {
            if (isGrantedNotification) {
                showNotification(notification)
            }
        } else {
            showNotification(notification)
        }


    }

    @SuppressLint("MissingPermission")
    private fun showNotification(notification: Notification) {
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

    private fun checkIfBuildVersionMoreThanO(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
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