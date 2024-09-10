package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository
) {

    suspend fun getCharacterList():List<CharacterModel>{
        return  repository.getCharacters()
    }

}