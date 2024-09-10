package com.example.harry_potter_and_retrofit.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterByIdUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = CharacterRepositoryImpl()
    private val getCharacterListUseCase = GetCharacterListUseCase(repository)
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private var _character: MutableStateFlow<CharacterModel> =
        MutableStateFlow(CharacterModel())

    val character = _character.asStateFlow()

    private var _characterList: MutableStateFlow<List<CharacterModel>> =
        MutableStateFlow<List<CharacterModel>>(mutableListOf())

    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _character.value = getCharacterByIdUseCase.getCharacterById()
                _characterList.value = getCharacterListUseCase.getCharacterList()
            } catch (t: Throwable) {
                Log.e("TAG", "${t.message}")
            }

        }
    }

    fun getRandomCharacter() {
        val size = _characterList.value.size
        val number = (0..size).random()
        val randomCharacter = _characterList.value[number]
        _character.value = randomCharacter
    }

}