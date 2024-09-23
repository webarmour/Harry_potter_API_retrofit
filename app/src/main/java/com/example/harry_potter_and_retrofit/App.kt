package com.example.harry_potter_and_retrofit

import android.app.Application
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : Application() {


    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set

    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this

        db = CharacterDatabase.getInstance(this)
        firebaseInstance = FirebaseUtils.getInstance(this)
        firebaseInstance.crashlytics.isCrashlyticsCollectionEnabled = false
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}