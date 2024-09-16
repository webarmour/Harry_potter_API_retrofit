package com.example.harry_potter_and_retrofit.data.localdb.databaase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harry_potter_and_retrofit.data.localdb.dao.CharacterDataAccessObject
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel


@Database(entities =
[CharacterDbModel::class],
    version = 1)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun getCharacterDao():CharacterDataAccessObject


}