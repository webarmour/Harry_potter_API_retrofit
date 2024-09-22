package com.example.harry_potter_and_retrofit.data.localdb.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "characters")
data class CharacterDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "character")
    val character: String,
    @ColumnInfo(name = "hogwarts_house")
    val hogwartsHouse: String,
    @ColumnInfo(name = "image_url")
    val image: String,
)