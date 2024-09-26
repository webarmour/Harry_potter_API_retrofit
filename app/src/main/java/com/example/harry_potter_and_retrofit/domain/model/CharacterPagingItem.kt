package com.example.harry_potter_and_retrofit.domain.model

data class CharacterPagingItem (
    val id: String,
    val name: String? = null,
    val bloodStatus: String? = null,
    val hogwartsHouse: String? = null,
    val patronus: String? = null,
    val imageUrl: String? = null
)