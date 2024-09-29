package com.example.harry_potter_and_retrofit.presentation.utils

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity

class NotificationUtils(
    private val application: Application,
) {
//
//    fun createNotificationChannel() {
//        if (checkIfBuildVersionMoreThanO()) {
//            val name = "Our Notification Channel"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val descriptionText = "test text"
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            val notificationManager =
//                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

//    fun createNotification() {
//
//        val intent = Intent(application, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            application,
//            0,
//            intent,
//            getCorrectFlagForPendingIntent()
//        )
////
//
//        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
//            .setSmallIcon(R.drawable.potterpng)
//            .setBadgeIconType(R.drawable.potterpng)
//            .setContentTitle("Привет")
//            .setContentText("Чекни мать")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        val isGrantedNotification =
//            ((application as App).permissionsService.checkPermissionForNotification())
//        if (isGrantedNotification != null) {
//            if (isGrantedNotification) {
//                showNotification(notification)
//            }
//        } else {
//            showNotification(notification)
//        }
//
//
//    }

//    @SuppressLint("MissingPermission")
//    private fun showNotification(notification: Notification) {
//        NotificationManagerCompat.from(application).notify(
//            NOTIFICATION_ID, notification
//        )
//    }

    @SuppressLint("MissingPermission")
    fun showNewNotification(
        channelId: String = "New channel id",
        @DrawableRes notificationIcon: Int = R.drawable.potterpng,
        notificationTitle: String = "Notification title",
        notificationContentText: String = "notification context text",
        notificationPriority: Int = NotificationCompat.PRIORITY_LOW,
        channelName: String = "New channel name",
        channelImportance: Int = NotificationManager.IMPORTANCE_LOW,
        channelDescription: String = "some channel description",
        autoCancel: Boolean = true
    ) {
        Log.d("NotificationDebug", "showNewNotification called")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,channelName,channelImportance).apply {
                description = channelDescription
            }
            val notificationManager =
                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d("NotificationDebug", "Notification channel created")

        }

        val notification = NotificationCompat.Builder(application, channelId)
            .setSmallIcon(notificationIcon)
            .setContentTitle(notificationTitle)
            .setContentText(notificationContentText)
            .setPriority(notificationPriority)
            .setAutoCancel(autoCancel)
            .build()
        Log.d("NotificationDebug", "Notification built")

        val isGrantedNotification =
            (application as App).permissionsService.checkPermissionForNotification()
        if (isGrantedNotification != null) {
            if (isGrantedNotification){
                NotificationManagerCompat.from(application).notify(
                    NOTIFICATION_ID, notification
                )
            } else {
                Toast.makeText(application,"Permission not granted", Toast.LENGTH_LONG).show()
                Log.d("NotificationDebug", "Permission not granted")
            }
        } else {
            NotificationManagerCompat.from(application).notify(NOTIFICATION_ID, notification)

            Log.d("NotificationDebug", "Notification sent")


        }

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
        private const val NOTIFICATION_ID = 2
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