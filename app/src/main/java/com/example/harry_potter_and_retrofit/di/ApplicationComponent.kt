package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModelFactory
import dagger.Component


@Component(
    modules = [DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        ContextModule::class,
        BindImpls::class
    ]
)

interface ApplicationComponent {


    fun characterListViewModelFactory(): CharacterListViewModelFactory

}