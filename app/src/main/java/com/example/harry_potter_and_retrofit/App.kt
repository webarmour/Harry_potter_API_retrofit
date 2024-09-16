package com.example.harry_potter_and_retrofit

import android.app.Application
import androidx.room.Room
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.data.localdb.migration.MIGRATION_1_2
import com.google.firebase.crashlytics.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : Application() {



    lateinit var db: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this
        db = Room
            .databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java,
                "db"
        )
            .build()

    }

   companion object {
       lateinit var INSTANCE: App
           private set
   }

}