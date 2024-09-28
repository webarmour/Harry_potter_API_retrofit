package com.example.harry_potter_and_retrofit

import android.app.Application
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.di.ContextModule
import com.example.harry_potter_and_retrofit.di.DaggerApplicationComponent
import com.example.harry_potter_and_retrofit.presentation.utils.NotificationUtils
import com.example.harry_potter_and_retrofit.presentation.utils.PermissionUtils
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : Application() {


    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set

    lateinit var notificationService: NotificationUtils
        private set

    lateinit var permissionsService: PermissionUtils
        private set

    override fun onCreate() {
        super.onCreate()

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.isCrashlyticsCollectionEnabled = true
        INSTANCE = this
        permissionsService = PermissionUtils.getInstance(this)
        db = CharacterDatabase.getInstance(this)

        firebaseInstance = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(App.INSTANCE))
            .build()
            .firebaseUtils()

        firebaseInstance.crashlytics.isCrashlyticsCollectionEnabled = false

        notificationService = NotificationUtils.getInstance(this)

        notificationService.createNotificationChannel()


    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}