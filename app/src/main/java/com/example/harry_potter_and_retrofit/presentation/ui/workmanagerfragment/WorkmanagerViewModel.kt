package com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorker


class WorkmanagerViewModel(
    private val context: Application,
) : AndroidViewModel(context) {

    init {
        CachingDataWorker.startWork()
    }

    fun testNotify() {
        (context as App).notificationService.createNotification()
    }

    fun startService() {
    CachingDataWorker.startWork()

    }


    fun stopService() {
        CachingDataWorker.stopWork()
    }


}