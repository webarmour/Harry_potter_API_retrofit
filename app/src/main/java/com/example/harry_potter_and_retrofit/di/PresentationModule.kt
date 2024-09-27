package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterUseCase
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class PresentationModule() {


    @Provides
    fun provideGetCharacterListUseCase(characterRepository: CharacterRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(characterRepository)
    }

    @Provides
    fun providesViewModel(getCharacterListUseCase: GetCharacterListUseCase): CharacterListViewModel {
        return CharacterListViewModel(getCharacterListUseCase)
    }

    @Provides
    fun provideCharacterListViewModelFactory(characterListViewModel: CharacterListViewModel): CharacterListViewModelFactory {
        return CharacterListViewModelFactory(characterListViewModel)
    }

    @Provides
    fun provideGetCharacterUseCase(characterRepository: CharacterRepository): GetCharacterUseCase {
        return GetCharacterUseCase(characterRepository)
    }

    @Provides
    fun providesViewModel(getCharacterUseCase: GetCharacterUseCase): MainViewModel {
        return MainViewModel(getCharacterUseCase)
    }

    @Provides
    fun provideCharacterListViewModelFactory(mainViewModel: MainViewModel): MainViewModelFactory {
        return MainViewModelFactory(mainViewModel)
    }
}