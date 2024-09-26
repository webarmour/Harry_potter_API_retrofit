package com.example.harry_potter_and_retrofit.lessons

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

private const val TAG = "HatJobIntentService"
class HatJobIntentService: JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
    
    
    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork: ")
        (1..100).forEach {
            Thread.sleep(3000)
            val house = arrayOf(
                "Hogwarts",
                "Slytherin",
                "Hufflepuff",
                "Ravenclaw"
            ).random()

            Log.d(TAG, "Strudent $it goes to $house")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    companion object {
        const val JOB_ID = 132
        fun getWorkIntent(context: Context) = Intent(context, HatJobIntentService::class.java)
        fun enqueueWork(context: Application) {
            enqueueWork(
                context,
                HatJobIntentService::class.java,
                JOB_ID,
                getWorkIntent(context)
            )
        }
    }

}