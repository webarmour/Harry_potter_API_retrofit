package com.example.harry_potter_and_retrofit.domain.repository

import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

interface CharacterRepository {


    suspend fun getCharacters(): List<CharacterItem>
    suspend fun getCharacterById(id: Int) : CharacterItem

}