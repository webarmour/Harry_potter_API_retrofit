package com.example.harry_potter_and_retrofit.data.network


import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import com.example.harry_potter_and_retrofit.data.network.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {

    private val mapper = CharacterModelMapper()

    override suspend fun getCharacters(): List<CharacterModel> {

        return mapper.listModelToListDto(RetrofitInstance.searchCharactersApi.getCharacters())
    }

    override suspend fun getCharacterById(id: Int): CharacterModel {
        return mapper.characterModelDtoToCharacterModel(RetrofitInstance.searchCharactersApi.getCharactersById(id))
    }
}


