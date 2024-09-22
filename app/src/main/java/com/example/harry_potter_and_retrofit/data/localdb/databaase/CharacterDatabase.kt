package com.example.harry_potter_and_retrofit.data.localdb.databaase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harry_potter_and_retrofit.data.localdb.dao.Dao
import com.example.harry_potter_and_retrofit.data.localdb.dbmodel.CharacterDbModel


@Database(
    entities =
    [CharacterDbModel::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): Dao


    companion object {

        private var INSTANCE: CharacterDatabase? = null
        private const val DB_NAME = "characters.db"
        private val LOCK = Any()

        fun getInstance(application: Application): CharacterDatabase {

            INSTANCE?.let { db ->
                return db
            }

            synchronized(LOCK) {

                INSTANCE?.let { db ->
                    return db
                }

                val db = Room
                    .databaseBuilder(
                        application,
                        CharacterDatabase::class.java,
                        DB_NAME
                    )
                    .build()

                INSTANCE = db
                return db

            }
        }
    }
}