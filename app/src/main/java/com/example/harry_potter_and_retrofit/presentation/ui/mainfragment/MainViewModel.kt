package com.example.harry_potter_and_retrofit.presentation.ui.mainfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(

    private val getCharacterUseCase: GetCharacterUseCase,

) : ViewModel() {

    private var _state = MutableStateFlow<ProgressState>(ProgressState.Success)
    var state = _state.asStateFlow()

    private var _character =
        MutableStateFlow(CharacterItem())

    val character = _character.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = ProgressState.Loading

            try {
                _character.value = getCharacterUseCase()
            } catch (t: Throwable) {
                Log.e("TAG", "${t.message}")
            }

            _state.value = ProgressState.Success

        }
    }

    fun getRandomCharacter() {
        viewModelScope.launch {
            runCatching {
                _state.value = ProgressState.Loading
                getCharacterUseCase((1..23).random())
            }.fold(
                onSuccess = { _character.value = it },
                onFailure = { Log.e("TAG", "${it.message}") }
            )
            _state.value = ProgressState.Success
        }
    }

}