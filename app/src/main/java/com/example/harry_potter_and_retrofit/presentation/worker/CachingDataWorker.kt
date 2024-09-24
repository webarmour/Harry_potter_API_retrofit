package com.example.harry_potter_and_retrofit.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CachingDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    val repo = CharacterRepositoryImpl(App.INSTANCE)
    val uploadDataUseCase = UploadCharacterListUseCase(repo)
    val cacheCharactersListUseCase = CacheCharactersListUseCase(repo)

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                cacheCharactersListUseCase(
                    uploadDataUseCase()
                )
                Result.success()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                Result.retry()
            }
        }
    }

    companion object {

        private val workManager = WorkManager.getInstance(App.INSTANCE)

        private fun getRequest() =
            OneTimeWorkRequestBuilder<CachingDataWorker>()
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
    }

}