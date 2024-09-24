package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.lessons.HatWorkManager


class WorkmanagerViewModel(
    private val context: Application,
) : AndroidViewModel(context) {


    fun testNotify() {
        (context as App).notificationService.createNotification()
    }

    fun startService() {


    }


    fun stopService() {

    }


}