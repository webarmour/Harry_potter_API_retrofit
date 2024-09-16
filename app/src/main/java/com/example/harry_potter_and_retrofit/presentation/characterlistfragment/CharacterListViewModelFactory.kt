package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase

class CharacterListViewModelFactory: ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)){
                val repo = CharacterRepositoryImpl()
                val useCase2 = GetCharacterListUseCase(repo)
                return CharacterListViewModel(repo, useCase2) as T
            } else {
                throw IllegalArgumentException("Unknown class name")
            }

        }
    }


