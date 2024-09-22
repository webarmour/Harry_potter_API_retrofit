package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke(id: Int = 1) = repository.getCharacterById(id)
}