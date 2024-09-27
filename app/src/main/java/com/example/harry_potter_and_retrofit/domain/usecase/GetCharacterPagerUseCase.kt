package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository

class GetCharacterPagerUseCase(
    private val repo: CharacterPagingRepository
) {

    operator fun invoke() = repo.getPager()
}