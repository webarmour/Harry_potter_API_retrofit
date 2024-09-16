package com.example.harry_potter_and_retrofit.data.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel


@Dao
interface CharacterDataAccessObject {

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<CharacterDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDbModel)

    @Update
    suspend fun editCharacter(character: CharacterDbModel)

    @Delete
    suspend fun deleteCharacter(character: CharacterDbModel)


}