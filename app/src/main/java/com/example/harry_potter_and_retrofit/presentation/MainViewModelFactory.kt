package com.example.harry_potter_and_retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterByIdUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            val repo = CharacterRepositoryImpl()
            val useCase = GetCharacterByIdUseCase(repo)
            val useCase2 = GetCharacterListUseCase(repo)
            return MainViewModel(repo, useCase2, useCase) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }

    }
}
