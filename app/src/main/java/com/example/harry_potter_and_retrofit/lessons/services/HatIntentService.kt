package com.example.harry_potter_and_retrofit.lessons.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.lessons.TAG

class HatIntentService : IntentService(SERVICE_NAME) {


    @Deprecated("Deprecated in Java")
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
//        setIntentRedelivery()
        createChannelForNotification()
        startForeground(NOTIFY_ID, createNotification())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }



    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        (1..100).forEach {
            Thread.sleep(3000)
            val house = arrayOf("Hogwarts", "Slytherin", "Hufflepuff", "Ravenclaw").random()

            Log.d(TAG, "Strudent $it goes to $house")
        }
    }

    fun createChannelForNotification() {
        val notificationManager =
            application.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel for foreground service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
            .setSmallIcon(R.drawable.potterpng)
            .setBadgeIconType(R.drawable.potterpng)
            .setContentTitle("The hat working...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        return notification
    }

    companion object {
        val SERVICE_NAME = "name"
        private const val CHANNEL_ID = "foreground service channel"
        private const val NOTIFY_ID = 23212
        fun getIntent(context: Context) = Intent(context, HatIntentService::class.java)
    }

}