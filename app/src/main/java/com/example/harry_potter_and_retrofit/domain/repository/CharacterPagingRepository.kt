package com.example.harry_potter_and_retrofit.domain.repository

import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem

interface CharacterPagingRepository {

    suspend fun getCharacters(): List<CharacterPagingItem>
}