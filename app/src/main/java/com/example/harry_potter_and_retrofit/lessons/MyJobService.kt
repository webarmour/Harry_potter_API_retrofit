package com.example.harry_potter_and_retrofit.lessons

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val TAG = "MyJobService"

class MyJobService : JobService() {

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


    override fun onStartJob(params: JobParameters?): Boolean {
        scope.launch {
            Log.d(TAG, "onStartJob: ")
            (1..100).forEach {
                delay(3000)
                val house = arrayOf(
                    "Hogwarts",
                    "Slytherin",
                    "Hufflepuff",
                    "Ravenclaw"
                ).random()

                Log.d(TAG, "Strudent $it goes to $house")
            }
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob: ")
        return true
    }

    companion object {
        const val JOB_ID = 1
        fun getJobService() = MyJobService::class.java

        fun startService(context: Context) {
            val jobService = ComponentName(context,getJobService())

            val jobInfo = JobInfo.Builder(JOB_ID, jobService)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .build()

            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)

        }
    }

}