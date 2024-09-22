package com.example.harry_potter_and_retrofit.data.network


import com.example.harry_potter_and_retrofit.data.network.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {

    private val mapper = CharacterModelMapper()

    override suspend fun getCharacters(): List<CharacterItem> {

        return mapper.listModelToListDto(RetrofitInstance.searchCharactersApi.getCharacters())
    }

    override suspend fun getCharacterById(id: Int): CharacterItem {
        return mapper.characterModelDtoToCharacterModel(RetrofitInstance.searchCharactersApi.getCharactersById(id))
    }
}


