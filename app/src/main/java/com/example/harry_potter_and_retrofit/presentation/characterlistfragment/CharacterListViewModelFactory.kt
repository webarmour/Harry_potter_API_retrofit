package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase

class CharacterListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            val repo = CharacterRepositoryImpl(App.INSTANCE)
            val uploadCharacterListUseCase = UploadCharacterListUseCase(repo)
            val getCharacterListUseCase = GetCharacterListUseCase(repo)
            val cacheCharacterListUseCase = CacheCharactersListUseCase(repo)
            return CharacterListViewModel(
                uploadCharacterListUseCase,
                getCharacterListUseCase,
                cacheCharacterListUseCase
            ) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }


    }
}


