package com.example.harry_potter_and_retrofit.presentation.worker

import android.app.NotificationManager
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


private const val TAG = "CachingDataWorker"

@HiltWorker
class CachingDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val uploadDataUseCase: UploadCharacterListUseCase,
    private val cacheCharactersListUseCase: CacheCharactersListUseCase,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        makeNotification("Started")
        (0..100 step 10).forEach {
            delay(100)
            setProgress(workDataOf(PROGRESS to it))
        }

        return withContext(Dispatchers.IO) {

            return@withContext try {
                cacheCharactersListUseCase(
                    uploadDataUseCase()
                )
                makeNotification("Finished")
                Result.success()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                makeNotification("Error")
                Result.retry()
            }
        }
    }

    private fun makeNotification(notificationContent: String) {
        App.INSTANCE.notificationService.showNewNotification(
            notificationContentText = notificationContent,
            notificationTitle = "Caching",
            channelImportance = NotificationManager.IMPORTANCE_DEFAULT
        )
    }


    companion object {

        private val workManager = WorkManager.getInstance(App.INSTANCE)
        fun getCurrentWorkManager() = workManager


        private fun getRequest() =
            OneTimeWorkRequestBuilder<CachingDataWorker>()
                .addTag(TAG_PROGRESS)
                .build()


        fun startWork() {
            workManager.beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                getRequest()
            ).enqueue()
        }

        fun stopWork() {
            workManager.cancelUniqueWork(WORK_NAME)
        }


        private const val WORK_NAME = "caching data into room db work"
        const val PROGRESS = "progress"
        const val TAG_PROGRESS = "tag_progress"
    }

}