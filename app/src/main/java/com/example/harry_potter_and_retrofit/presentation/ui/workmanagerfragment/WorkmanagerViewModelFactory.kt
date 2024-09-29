package com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.workDataOf
import com.example.harry_potter_and_retrofit.App

class WorkmanagerViewModelFactory(private val workmanagerViewModel: WorkmanagerViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkmanagerViewModel::class.java)) {
            return workmanagerViewModel as T
        }
        throw RuntimeException("Unknown class name")
    }
}