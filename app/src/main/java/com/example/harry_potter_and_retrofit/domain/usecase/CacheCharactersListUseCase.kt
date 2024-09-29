package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import javax.inject.Inject

class CacheCharactersListUseCase @Inject constructor(
    private val repo: CharacterRepository,
) {


    suspend operator fun invoke(charactersList: List<CharacterItem>) =
        repo.saveCharacterListToLocalDb(charactersList)
}