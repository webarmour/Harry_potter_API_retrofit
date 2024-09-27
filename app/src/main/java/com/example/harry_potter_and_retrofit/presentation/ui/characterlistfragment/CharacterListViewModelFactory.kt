package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterListViewModelFactory(private val characterListViewModel: CharacterListViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {

            return characterListViewModel as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }


    }
}


