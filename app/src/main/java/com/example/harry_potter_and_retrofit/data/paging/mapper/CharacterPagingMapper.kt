package com.example.harry_potter_and_retrofit.data.paging.mapper

import com.example.harry_potter_and_retrofit.data.paging.dto.DataJson
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem

class CharacterPagingMapper {


    fun characterDtoToPaging(dtoList: List<DataJson>) =
        dtoList.map {
            CharacterPagingItem(
                it.id,
                it.attribute.name,
                it.attribute.bloodStatus,
                it.attribute.hogwartsHouse,
                it.attribute.patronus,
                it.attribute.imageUrl
            )
        }


}