package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment.WorkmanagerViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorkerFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        ContextModule::class,
        BindImpls::class,
        FirebaseModule::class
    ]
)

interface ApplicationComponent {


    fun characterListViewModelFactory(): CharacterListViewModelFactory
    fun mainFragmentViewModelFactory(): MainViewModelFactory
    fun forumFragmentViewModelFactory():ForumViewModelFactory
    fun workmanagerViewModelFactory():WorkmanagerViewModelFactory
    fun cachingDataWorkerFactory():CachingDataWorkerFactory

    fun firebaseUtils(): FirebaseUtils

}