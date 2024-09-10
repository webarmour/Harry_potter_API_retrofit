package com.example.harry_potter_and_retrofit.presentation

import androidx.lifecycle.ViewModel
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterByIdUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase

class MainViewModel : ViewModel() {
    private val repository = CharacterRepositoryImpl()
    private val getCharacterListUseCase = GetCharacterListUseCase(repository)
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    val obj = getCharacterByIdUseCase.getCharacterById(1)
}