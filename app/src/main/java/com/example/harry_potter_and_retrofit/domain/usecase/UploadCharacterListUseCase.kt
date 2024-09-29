package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import javax.inject.Inject

class UploadCharacterListUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke() = repository.getCharactersFromNetwork()
}