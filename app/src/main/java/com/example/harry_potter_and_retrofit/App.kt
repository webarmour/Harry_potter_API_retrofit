package com.example.harry_potter_and_retrofit

import android.app.Application
import androidx.room.Room
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase

class App: Application() {

    lateinit var db: CharacterDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext,
            CharacterDatabase::class.java,
            "main_db").build()

    }

}