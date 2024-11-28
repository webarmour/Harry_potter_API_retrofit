package com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PagingViewModelFactory @Inject constructor(
    private val pagingViewModel: PagingViewModel,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PagingViewModel::class.java)) {
            pagingViewModel as T
        } else {
            throw RuntimeException("Unknown class name : $modelClass")
        }

    }
}