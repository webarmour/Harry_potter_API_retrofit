package com.example.harry_potter_and_retrofit.presentation.ui.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val repo = CharacterRepositoryImpl(App.INSTANCE)
            val getCharacterUseCase = GetCharacterUseCase(repo)
            return MainViewModel(
                getCharacterUseCase,
            ) as T
        } else {
            throw RuntimeException("Unknown class name")
        }

    }
}
