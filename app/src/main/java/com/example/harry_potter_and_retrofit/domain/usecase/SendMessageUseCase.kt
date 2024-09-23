package com.example.harry_potter_and_retrofit.domain.usecase

import com.example.harry_potter_and_retrofit.domain.model.ForumItem
import com.example.harry_potter_and_retrofit.domain.repository.ForumRepository

class SendMessageUseCase(
    private val repo: ForumRepository
) {

    operator fun invoke(text: String) {
        repo.sendMessage(text)
    }

}