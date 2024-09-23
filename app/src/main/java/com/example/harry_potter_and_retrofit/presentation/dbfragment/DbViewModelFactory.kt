package com.example.harry_potter_and_retrofit.presentation.dbfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.App

class DbViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DbViewModel::class.java)) {
            return DbViewModel(App.INSTANCE) as T
        }
        throw RuntimeException("Unknown class name")
    }
}