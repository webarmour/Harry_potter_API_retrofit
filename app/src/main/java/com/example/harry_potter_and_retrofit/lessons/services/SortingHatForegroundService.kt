package com.example.harry_potter_and_retrofit.lessons.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.lessons.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SortingHatForegroundService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        createChannelForNotification()
        startForeground(NOTIFY_ID, createNotification())
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        scope.launch {

            (1..100).forEach {
                delay(3000)
                val house = arrayOf("Hogwarts", "Slytherin", "Hufflepuff", "Ravenclaw").random()

                Log.d(TAG, "Strudent $it goes to $house")
            }
        }



        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {

        TODO()

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
        private const val CHANNEL_ID = "foreground service channel"
        private const val NOTIFY_ID = 2312
        fun getIntent(context: Context) = Intent(context, SortingHatForegroundService::class.java)
    }

}