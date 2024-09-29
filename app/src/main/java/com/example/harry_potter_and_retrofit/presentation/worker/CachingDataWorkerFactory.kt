package com.example.harry_potter_and_retrofit.presentation.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase

class CachingDataWorkerFactory(
    private val uploadDataUseCase: UploadCharacterListUseCase,
    private val cacheCharactersListUseCase: CacheCharactersListUseCase,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker {
        return CachingDataWorker(
            appContext,
            workerParameters,
            uploadDataUseCase,
            cacheCharactersListUseCase
        )
    }

}