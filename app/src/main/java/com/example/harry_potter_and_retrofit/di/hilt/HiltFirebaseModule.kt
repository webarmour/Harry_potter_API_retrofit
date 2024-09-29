package com.example.harry_potter_and_retrofit.di.hilt

import android.app.Application
import com.example.harry_potter_and_retrofit.data.ForumRepositoryImpl
import com.example.harry_potter_and_retrofit.data.firebase.AuthUtils
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.domain.repository.ForumRepository
import com.example.harry_potter_and_retrofit.domain.usecase.SendMessageUseCase
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModelFactory
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltFirebaseModule {

    @Provides
    fun providesDbFirebase(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    fun providesFirebaseCrashlytics(): FirebaseCrashlytics {
        return Firebase.crashlytics
    }

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun providesAuthUI(): AuthUI {
        return AuthUI.getInstance()
    }

    @Provides
    fun providesAuthUtils(auth: FirebaseAuth, authUI: AuthUI): AuthUtils {
        return AuthUtils(auth, authUI)
    }

    @Provides
    fun provideFirebaseUtils(
        firebaseDatabase: FirebaseDatabase,
        firebaseCrashlytics: FirebaseCrashlytics,
        firebaseAuthUtils: AuthUtils,
    ): FirebaseUtils {
        return FirebaseUtils(firebaseDatabase, firebaseCrashlytics, firebaseAuthUtils)
    }


    @Provides
    fun providesForumRepoImpl(
        application: Application,
        firebaseUtils: FirebaseUtils,
    ): ForumRepositoryImpl {
        return ForumRepositoryImpl(application, firebaseUtils)
    }

    @Provides
    fun provideSendMessageUseCase(forumRepository: ForumRepository): SendMessageUseCase {
        return SendMessageUseCase(forumRepository)
    }

//    @Provides
//    fun providesForumVieModel(sendMessageUseCase: SendMessageUseCase): ForumViewModel {
//        return ForumViewModel(sendMessageUseCase)
//    }
//
//    @Provides
//    fun provideForumViewModelFactory(forumViewModel: ForumViewModel): ForumViewModelFactory {
//        return ForumViewModelFactory(forumViewModel)
//    }
}