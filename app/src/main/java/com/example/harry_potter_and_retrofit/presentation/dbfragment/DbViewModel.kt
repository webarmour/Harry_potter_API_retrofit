package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.localdb.dao.CharacterDataAccessObject
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DbViewModel : ViewModel() {

    private lateinit var characterDao: CharacterDataAccessObject

        private var _characters = MutableStateFlow<List<CharacterDbModel>>(mutableListOf())
    val characters = _characters.asStateFlow()

//    val characters = characterDao.getAllCharacters()
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(500L),
//            mutableListOf()
//        )
//

    fun initDao(application: Application?) {
        characterDao = (application as App).db.getCharacterDao()
    }

    init {
        viewModelScope.launch {

        }
    }

    fun onBtnAdd() {
        var size = _characters.value.size

        viewModelScope.launch {
            characterDao.insertCharacter(CharacterDbModel(hogwartsHouse = "Slytherin", image = "image.png", character = "Potter ${size}"))

        }
        updateTextView()
    }


    fun btDelete() {
        viewModelScope.launch {
            characters.value.lastOrNull()?.let {
                characterDao.deleteCharacter(it)
            }


        }
        updateTextView()
    }

    fun btUpdate() {
        viewModelScope.launch {
            characters.value.lastOrNull()?.let {
                characterDao.editCharacter(it.copy(character = "Severus"))
            }


        }
        updateTextView()
    }

    private fun updateTextView(){
        viewModelScope.launch {
            _characters.value = characterDao.getAllCharacters()
        }
    }
}