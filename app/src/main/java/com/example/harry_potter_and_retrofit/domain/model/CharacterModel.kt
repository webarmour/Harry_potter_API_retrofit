package com.example.harry_potter_and_retrofit.domain.model

data class CharacterModel(
    val id: Int = 0,
    val character: String = "Unknown",
    val hogwartsHouse: String = "Unknown",
    val image: String = "Unknown"
)
