package com.example.harry_potter_and_retrofit.data.network.mapper

import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem


class CharacterModelMapper {


    fun characterModelDtoToCharacterModel(characterDto: CharacterDataTransferObject) = CharacterItem(
        characterDto.id,
        characterDto.name,
        characterDto.hogwartsHouse,
        characterDto.imageUrl
    )

    fun listModelToListDto(dtoList: List<CharacterDataTransferObject>): List<CharacterItem> {
        val resList = mutableListOf<CharacterItem>()
        dtoList.forEach{resList.add(characterModelDtoToCharacterModel(it))}
        return resList
    }



}