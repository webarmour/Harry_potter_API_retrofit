package com.example.harry_potter_and_retrofit.presentation.mainfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterByIdUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import com.example.harry_potter_and_retrofit.presentation.ProgressState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CharacterRepositoryImpl,
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<ProgressState>(ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character =
        MutableStateFlow(CharacterModel())

    val character = _character.asStateFlow()

    private var _characterList =
        MutableStateFlow<List<CharacterModel>>(mutableListOf())

    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {

                _character.value = getCharacterByIdUseCase.getCharacterById()
                _characterList.value = getCharacterListUseCase.getCharacterList()
            } catch (t: Throwable) {
                Log.e("TAG", "${t.message}")
            }

            _state.value = ProgressState.Success

        }
    }

    fun getRandomCharacter() {

        viewModelScope.launch {
            _state.value = ProgressState.Loading
            try {
                val upperIndex = _characterList.value.size - 1
                _character.value =
                    getCharacterByIdUseCase.getCharacterById((0..upperIndex).random())
            } catch (t: Throwable) {
                Log.e("TAG", "${t.message}")
            }
            _state.value = ProgressState.Success
        }

    }

}