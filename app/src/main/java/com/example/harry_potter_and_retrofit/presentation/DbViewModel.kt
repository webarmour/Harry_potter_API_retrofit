package com.example.harry_potter_and_retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class DbViewModel : ViewModel() {



    val characterDao = (MainActivity().application as App)
        .db.getCharacterDao()

    val allCharacters = characterDao.getAllCharacters()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    fun onBtnAdd() {

        characterDao.insertCharacter(
            CharacterDbModel(
                id=1,
                character = "Potter ",
                "",
                ""
            )
        )
    }
}