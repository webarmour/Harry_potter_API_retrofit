package com.example.harry_potter_and_retrofit

import android.app.Application
import androidx.work.Configuration
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.di.ApplicationComponent
import com.example.harry_potter_and_retrofit.di.ContextModule
import com.example.harry_potter_and_retrofit.di.DaggerApplicationComponent
import com.example.harry_potter_and_retrofit.presentation.utils.NotificationUtils
import com.example.harry_potter_and_retrofit.presentation.utils.PermissionUtils
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App() : Application(), Configuration.Provider {


    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set

    lateinit var notificationService: NotificationUtils
        private set

    lateinit var permissionsService: PermissionUtils
        private set

    lateinit var appComponent: ApplicationComponent
        private set


    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this

        appComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()

        permissionsService = PermissionUtils.getInstance(this)
        db = CharacterDatabase.getInstance(this)

        firebaseInstance = appComponent.firebaseUtils()

        firebaseInstance.crashlytics.isCrashlyticsCollectionEnabled = false

        notificationService = NotificationUtils.getInstance(this)


    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(appComponent.cachingDataWorkerFactory())
            .build()


    companion object {
        lateinit var INSTANCE: App
            private set
    }
}