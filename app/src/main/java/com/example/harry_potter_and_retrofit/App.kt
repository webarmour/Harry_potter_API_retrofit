package com.example.harry_potter_and_retrofit

import android.app.Application
import androidx.hilt.work.HiltWorker
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.presentation.utils.NotificationUtils
import com.example.harry_potter_and_retrofit.presentation.utils.PermissionUtils
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App() : Application(), Configuration.Provider {


    lateinit var db: CharacterDatabase
        private set

    @Inject
    lateinit var firebaseInstance: FirebaseUtils


    lateinit var notificationService: NotificationUtils
        private set

    lateinit var permissionsService: PermissionUtils
        private set

    @Inject
    lateinit var cachingWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this



        permissionsService = PermissionUtils.getInstance(this)
        db = CharacterDatabase.getInstance(this)

//        firebaseInstance = appComponent.firebaseUtils()

        firebaseInstance.crashlytics.isCrashlyticsCollectionEnabled = false

        notificationService = NotificationUtils.getInstance(this)


    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(cachingWorkerFactory)
            .build()


    companion object {
        lateinit var INSTANCE: App
            private set
    }
}