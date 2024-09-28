package com.example.harry_potter_and_retrofit.presentation.ui.forumfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForumViewModelFactory(
    private val forumViewModel: ForumViewModel,
    ) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)) {

            return forumViewModel as T
        } else {
            throw RuntimeException("Cannot recognize viewModel class")
        }
    }
}