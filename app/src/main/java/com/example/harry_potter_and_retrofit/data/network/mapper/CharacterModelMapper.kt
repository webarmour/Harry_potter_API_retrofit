package com.example.harry_potter_and_retrofit.data.network.mapper

import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import com.example.harry_potter_and_retrofit.domain.model.CharacterModel


class CharacterModelMapper {


    fun characterModelDtoToCharacterModel(characterDto: CharacterDataTransferObject) = CharacterModel(
        characterDto.id,
        characterDto.name,
        characterDto.hogwartsHouse,
        characterDto.imageUrl
    )

    fun listModelToListDto(dtoList: List<CharacterDataTransferObject>): List<CharacterModel> {
        val resList = mutableListOf<CharacterModel>()
        dtoList.forEach{resList.add(characterModelDtoToCharacterModel(it))}
        return resList
    }



}