package com.example.harry_potter_and_retrofit.di

import android.content.Context
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListFragment
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumFragment
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.forumfragment.ForumViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainFragment
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment.PagingFragment
import com.example.harry_potter_and_retrofit.presentation.ui.pagingfragment.PagingViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment.WorkmanagerFragment
import com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment.WorkmanagerViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//@Singleton
//@Component(
//    modules = [DataModule::class,
//        DomainModule::class,
//        PresentationModule::class,
//        ContextModule::class,
//        BindImpls::class,
//        FirebaseModule::class
//    ]
//)

interface ApplicationComponent {


//    fun cachingDataWorkerFactory():CachingDataWorkerFactory
//
//    fun firebaseUtils(): FirebaseUtils
//
//    fun inject(app: App)
//    fun injectWorkmanagerFragment(workmanagerFragment: WorkmanagerFragment)
//    fun injectMainFragment(mainFragment: MainFragment)
//    fun injectCharacterListFragment(characterListFragment: CharacterListFragment)
//    fun injectForumFragment(forumFragment: ForumFragment)
//    fun injectPagingFragment(pagingFragment: PagingFragment)

}