package com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorker


class WorkmanagerViewModel(
    val context: Application,
) : ViewModel() {


    internal val progressWorkInfoItems: LiveData<List<WorkInfo>>


    init {
       progressWorkInfoItems = CachingDataWorker.getCurrentWorkManager().getWorkInfosByTagLiveData(CachingDataWorker.TAG_PROGRESS)
        CachingDataWorker.startWork()
    }


    fun startService() {
    CachingDataWorker.startWork()


    }


    fun stopService() {
        CachingDataWorker.stopWork()
    }


}