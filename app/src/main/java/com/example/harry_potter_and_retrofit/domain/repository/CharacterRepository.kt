package com.example.harry_potter_and_retrofit.domain.repository

import com.example.harry_potter_and_retrofit.domain.model.CharacterModel

interface CharacterRepository {


    suspend fun getCharacters(): List<CharacterModel>
    suspend fun getCharacterById(id: Int) : CharacterModel

}