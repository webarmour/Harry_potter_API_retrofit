package com.example.harry_potter_and_retrofit.data.network


import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {


    override suspend fun getCharacters(): List<CharacterDataTransferObject> {
        return RetrofitInstance.searchCharactersApi.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): CharacterDataTransferObject {
        return RetrofitInstance.searchCharactersApi.getCharactersById(id)
    }
}


