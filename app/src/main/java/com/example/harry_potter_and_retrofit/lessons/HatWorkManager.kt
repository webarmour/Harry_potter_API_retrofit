package com.example.harry_potter_and_retrofit.lessons

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.harry_potter_and_retrofit.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


private const val TAG = "HatWorkManager"

class HatWorkManager(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {

        return withContext(Dispatchers.IO) {
            Log.d(TAG, "doWork: ")
            return@withContext try {

                val startIndex = inputData.getInt(KEY_START_STUDENT, 1)

                (startIndex..100).forEach {
                    delay(3000)
                    val house = arrayOf(
                        "Hogwarts",
                        "Slytherin",
                        "Hufflepuff",
                        "Ravenclaw"
                    ).random()

                    Log.d(TAG, "Strudent $it goes to $house")
                }
                val stringText = "Work is Done"
                Result.success(workDataOf(KEY_OUTPUT to stringText))

            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                Log.d(TAG, "doWork: $throwable")
                Result.retry()
            }
        }

    }


    companion object {

        private val workManager = WorkManager.getInstance(App.INSTANCE)

        private fun getInputData(value: Int): Data {
            val builder = Data.Builder()
            builder.putInt(KEY_START_STUDENT, value)
            return builder.build()
        }

        private fun getRequest(value: Int) = OneTimeWorkRequestBuilder<HatWorkManager>()
            .setInputData(getInputData(value))
            .build()


        fun startWork(value: Int = 1) {
            workManager.beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                getRequest(value)
            ).enqueue()
        }

        fun stopWork() {
            workManager.cancelUniqueWork(WORK_NAME)
        }

        const val KEY_START_STUDENT = "key start student"
        const val KEY_OUTPUT = "key"
        const val WORK_NAME = "fok"
    }
}