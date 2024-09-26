package com.example.harry_potter_and_retrofit.data.paging.repoimpl

import com.example.harry_potter_and_retrofit.data.paging.api.RetrofitInstance
import com.example.harry_potter_and_retrofit.data.paging.mapper.CharacterPagingMapper
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository

class CharactersPagingRepositoryImpl : CharacterPagingRepository {

    private val mapper = CharacterPagingMapper()


    override suspend fun getCharacters(): List<CharacterPagingItem> {
        return mapper.characterDtoToPaging(
            RetrofitInstance.searchCharactersPagingApi.getCharacters().data
        )
    }

}