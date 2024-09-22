package com.example.harry_potter_and_retrofit.data.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel


@Dao
interface Dao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterDbModel>

    @Query("SELECT * FROM characters WHERE id= :id")
    suspend fun getCharacterById(id: Int): CharacterDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterItem(character: CharacterDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characters: List<CharacterDbModel>)

    @Query("DELETE FROM characters")
    suspend fun deleteAll()


}