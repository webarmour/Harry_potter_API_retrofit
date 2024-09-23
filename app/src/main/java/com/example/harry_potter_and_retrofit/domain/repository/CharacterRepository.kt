package com.example.harry_potter_and_retrofit.domain.repository

import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

interface CharacterRepository {

    //Network
    suspend fun getCharactersFromNetwork(): List<CharacterItem>
    suspend fun getCharacterByIdFromNetwork(id: Int) : CharacterItem

    //Local
    suspend fun saveCharacterToLocalDb(characterItem: CharacterItem)
    suspend fun saveCharacterListToLocalDb(characterList: List<CharacterItem>)
    suspend fun getCharacterFromLocalDb(id: Int): CharacterItem
    suspend fun getCharacterListFromLocalDb(): List<CharacterItem>

}