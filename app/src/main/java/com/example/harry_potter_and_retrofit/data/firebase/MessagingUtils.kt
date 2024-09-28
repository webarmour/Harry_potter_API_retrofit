package com.example.harry_potter_and_retrofit.data.firebase

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.harry_potter_and_retrofit.R
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MessagingUtils(
    private val msgInstance : FirebaseMessaging
) {


//    private val msgInstance = FirebaseMessaging.getInstance()


    fun logToken(tag: String = "messageToken") {
        msgInstance.token.addOnCompleteListener {
            if (it.isSuccessful) {

                Log.d(tag, it.result)
                return@addOnCompleteListener

            } else {
                Log.d(tag, it.result)

            }
        }
    }

    inner class MyFirebaseMessagingService1() : FirebaseMessagingService() {

        override fun onMessageReceived(message: RemoteMessage) {
            super.onMessageReceived(message)

            val notification = NotificationCompat.Builder(this, "FCM")
                .setSmallIcon(R.drawable.potterpng)
                .setContentTitle(message.data["message"])
                .setContentText(message.data["message"] + convertToDate(message.data["timestamp"]))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                NotificationManagerCompat.from(this)
                    .notify(Random.nextInt(), notification)
            }


        }



        override fun onNewToken(token: String) {
            super.onNewToken(token)
        }

        private fun convertToDate(timestamp: String?): String {
            timestamp ?: return ""
            return SimpleDateFormat("dd.mm.yyyy", Locale.getDefault())
                .format(Date(timestamp.toLong() * 1000))

        }
    }

}