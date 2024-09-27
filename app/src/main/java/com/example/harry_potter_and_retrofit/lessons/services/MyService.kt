package com.example.harry_potter_and_retrofit.lessons.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.harry_potter_and_retrofit.lessons.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService: Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       val index =  intent?.getIntExtra(KEY_EXTRA, 1)
        scope.launch {
            if (index != null) {
                (index..100).forEach {
                    delay(3000)
                    val house = arrayOf("Hogwarts", "Slytherin", "Hufflepuff", "Ravenclaw").random()

                    Log.d(TAG, "Strudent $it goes to $house")
                }
            }
        }


        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")

    }

    companion object {
        private const val KEY_EXTRA = "index"
        fun getIntent(context: Context) = Intent(context, MyService::class.java).apply {
            putExtra(KEY_EXTRA, 20)
        }
    }

}