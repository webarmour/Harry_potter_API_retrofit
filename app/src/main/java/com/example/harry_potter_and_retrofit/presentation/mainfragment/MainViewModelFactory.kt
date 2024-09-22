package com.example.harry_potter_and_retrofit.presentation.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            val repo = CharacterRepositoryImpl()
            val useCase = UploadCharacterUseCase(repo)
            val useCase2 = UploadCharacterListUseCase(repo)
            return MainViewModel(useCase2, useCase) as T
        } else {
            throw RuntimeException("Unknown class name")
        }

    }
}
