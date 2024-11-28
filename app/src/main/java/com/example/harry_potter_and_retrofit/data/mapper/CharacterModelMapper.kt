package com.example.harry_potter_and_retrofit.data.mapper

import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel
import com.example.harry_potter_and_retrofit.data.network.dto.CharacterDataTransferObject
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import javax.inject.Inject


class CharacterModelMapper @Inject constructor(){

    fun characterModelDtoToCharacterModel(characterDto: CharacterDataTransferObject) =
        CharacterItem(
            characterDto.id,
            characterDto.name,
            characterDto.hogwartsHouse,
            characterDto.imageUrl
        )


    fun characterModelToModelDto(characterDto: CharacterItem) =
        CharacterDataTransferObject(
            characterDto.id,
            characterDto.character,
            characterDto.hogwartsHouse,
            characterDto.image
        )

    fun listDtoTolistModel(dtoList: List<CharacterDataTransferObject>) =
        dtoList.map {
            characterModelDtoToCharacterModel(it)
        }

    fun listModelToDtoList(listModel: List<CharacterItem>) =
        listModel.map { characterModelToModelDto(it) }


    fun mapModelToDbModel(characterItem: CharacterItem) =
        CharacterDbModel(
            characterItem.id,
            characterItem.character,
            characterItem.hogwartsHouse,
            characterItem.image
        )

    fun mapListModelToDtoListModel(charactersList: List<CharacterItem>) =
        charactersList.map { mapModelToDbModel(it) }


    fun mapDbModelToModel(characterItem: CharacterDbModel) =
        CharacterItem(
            characterItem.id,
            characterItem.character,
            characterItem.hogwartsHouse,
            characterItem.image
        )

    fun mapDbListModelToListModel(charactersList: List<CharacterDbModel>) =
        charactersList.map { mapDbModelToModel(it) }

}