package com.example.harry_potter_and_retrofit

import android.app.Application
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : Application() {


    lateinit var db: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this

        db = CharacterDatabase.getInstance(INSTANCE)

    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}