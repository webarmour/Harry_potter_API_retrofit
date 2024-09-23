package com.example.harry_potter_and_retrofit.presentation.dbfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class DbViewModel(
    private val context: Application,
) : AndroidViewModel(context) {

    private val characterDao = (context as App).db.getCharacterDao()

    private var _characters = MutableStateFlow<List<CharacterDbModel>>(mutableListOf())
    val characters = _characters.asStateFlow()

//    val characters = characterDao.getAllCharacters()
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(500L),
//            mutableListOf()
//        )
//



    fun onBtnAdd() {

        var size = _characters.value.size

        viewModelScope.launch {
            characterDao.insertCharacterItem(
                CharacterDbModel(
                    id = 0,
                    hogwartsHouse = "Slytherin",
                    image = "image.png",
                    character = "Potter ${characters.value.size}"
                )
            )

        }
        updateTextView()
    }


    fun btDelete() {
        viewModelScope.launch {
            characters.value.lastOrNull()?.let {
                characterDao.deleteAll()
            }


        }
        updateTextView()
    }


    private fun updateTextView() {
        viewModelScope.launch {
            _characters.value = characterDao.getAllCharacters()
        }
    }
}