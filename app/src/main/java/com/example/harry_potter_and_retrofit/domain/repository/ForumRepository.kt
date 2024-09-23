package com.example.harry_potter_and_retrofit.domain.repository

import com.example.harry_potter_and_retrofit.domain.model.ForumItem

interface ForumRepository {

    fun sendMessage(text: String)

}