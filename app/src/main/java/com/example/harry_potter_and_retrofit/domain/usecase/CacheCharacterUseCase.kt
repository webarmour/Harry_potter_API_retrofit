package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository

class CacheCharacterUseCase(
    private val repo: CharacterRepository
) {


    suspend operator fun invoke(characterItem: CharacterItem) =
        repo.saveCharacterToLocalDb(characterItem = characterItem)
}