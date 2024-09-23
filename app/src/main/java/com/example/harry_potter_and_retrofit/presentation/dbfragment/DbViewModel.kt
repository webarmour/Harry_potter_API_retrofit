package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class DbViewModel(
    private val context: Application,
) : AndroidViewModel(context) {

    fun testNotify() {
        (context as App).notificationService.createNotification()
    }


}