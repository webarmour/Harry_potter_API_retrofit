package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase

class CharacterListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            val repo = CharacterRepositoryImpl(App.INSTANCE)

            val getCharacterListUseCase = GetCharacterListUseCase(repo)

            return CharacterListViewModel(

                getCharacterListUseCase,

                ) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }


    }
}


