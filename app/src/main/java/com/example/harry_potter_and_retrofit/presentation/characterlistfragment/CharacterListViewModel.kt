package com.example.harry_potter_and_retrofit.presentation.characterlistfragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.data.network.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repo: CharacterRepositoryImpl,
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    val onlySlytherin = MutableStateFlow(false)

    private var _characterList =
        MutableStateFlow<List<CharacterModel>>(mutableListOf())

    val characterList: StateFlow<List<CharacterModel>> =
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

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {

            runCatching {
                _isLoading.value = true
                getCharacterListUseCase.getCharacterList()
            }.fold(
                onSuccess = { _characterList.value = it },
                onFailure = { Log.e(TAG, "${it.message}", it)}
            )
            _isLoading.value = false

        }

    }


}