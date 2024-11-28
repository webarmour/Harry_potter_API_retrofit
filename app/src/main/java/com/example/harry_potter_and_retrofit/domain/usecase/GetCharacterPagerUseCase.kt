package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository
import javax.inject.Inject

class GetCharacterPagerUseCase @Inject constructor(
    private val repo: CharacterPagingRepository,
) {

    operator fun invoke() = repo.getPager()
}