package com.example.harry_potter_and_retrofit.presentation.ui.forumfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.ForumRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.SendMessageUseCase

class ForumViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)){
            val repo = ForumRepositoryImpl(App.INSTANCE)
            val sendMessage = SendMessageUseCase(repo)
            return ForumViewModel(sendMessage) as T
        } else {
            throw RuntimeException("Cannot recognize viewModel class")
        }
    }
}