package com.example.harry_potter_and_retrofit.presentation.mainfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase
import com.example.harry_potter_and_retrofit.presentation.ProgressState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val uploadCharacterListUseCase: UploadCharacterListUseCase,
    private val getCharacterByIdUseCase: UploadCharacterUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<ProgressState>(ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character =
        MutableStateFlow(CharacterItem())

    val character = _character.asStateFlow()

    private var _characterList =
        MutableStateFlow<List<CharacterItem>>(mutableListOf())

    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading

            try {

                _character.value = getCharacterByIdUseCase()
                _characterList.value = uploadCharacterListUseCase()
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
                    getCharacterByIdUseCase((0..upperIndex).random())
            } catch (t: Throwable) {
                Log.e("TAG", "${t.message}")
            }
            _state.value = ProgressState.Success
        }

    }

}