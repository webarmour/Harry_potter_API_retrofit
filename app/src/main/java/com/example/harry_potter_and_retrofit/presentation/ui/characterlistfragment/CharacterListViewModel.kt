package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterListViewModel @Inject constructor(

    private val getCharacterListUseCase: GetCharacterListUseCase,

    ) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    val onlySlytherin = MutableStateFlow(false)

    private var _characterList =
        MutableStateFlow<List<CharacterItem>>(mutableListOf())

    init {
        getCharacters()
    }

    val characterList: StateFlow<List<CharacterItem>> =
        combine(_characterList, onlySlytherin) { characters, filterOn ->
            if (filterOn) {
                characters.filter {
                    it.hogwartsHouse == "Slytherin"
                }
            } else {
                characters
            }

        }.stateIn(
            viewModelScope, SharingStarted.Eagerly, _characterList.value
        )


    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _isLoading.value = true
                getCharacterListUseCase()
            }.fold(
                onSuccess = { _characterList.value = it },
                onFailure = { Log.e(TAG, "${it.message}", it) }
            )
            _isLoading.value = false

        }
    }

    fun refresh() {
        getCharacters()
    }


}