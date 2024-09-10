package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.model.CharacterModel
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {

    suspend fun getCharacterById(id:Int):CharacterModel{
        return repository.getCharacterById(id)
    }

}