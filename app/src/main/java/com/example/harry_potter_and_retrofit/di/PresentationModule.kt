package com.example.harry_potter_and_retrofit.di

import android.app.Application
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment.CharacterListViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.MainViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment.WorkmanagerViewModel
import com.example.harry_potter_and_retrofit.presentation.ui.workmanagerfragment.WorkmanagerViewModelFactory
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorker
import com.example.harry_potter_and_retrofit.presentation.worker.CachingDataWorkerFactory
import dagger.Module
import dagger.Provides


@Module
class PresentationModule() {


    @Provides
    fun providesViewModel(getCharacterListUseCase: GetCharacterListUseCase): CharacterListViewModel {
        return CharacterListViewModel(getCharacterListUseCase)
    }

    @Provides
    fun provideCharacterListViewModelFactory(characterListViewModel: CharacterListViewModel): CharacterListViewModelFactory {
        return CharacterListViewModelFactory(characterListViewModel)
    }


    @Provides
    fun providesMainViewModel(getCharacterUseCase: GetCharacterUseCase): MainViewModel {
        return MainViewModel(getCharacterUseCase)
    }

    @Provides
    fun provideMainViewModelFactory(mainViewModel: MainViewModel): MainViewModelFactory {
        return MainViewModelFactory(mainViewModel)
    }

    @Provides
    fun provideWorkmanagerViewModel(application: Application): WorkmanagerViewModel {
        return WorkmanagerViewModel(application)
    }


    @Provides
    fun provideWorkerViewModelFactory(workmanagerViewModel: WorkmanagerViewModel): WorkmanagerViewModelFactory {
        return WorkmanagerViewModelFactory(workmanagerViewModel)
    }


    @Provides
    fun provideCachingDataWorkerFactory(uploadCharacterListUseCase: UploadCharacterListUseCase, cacheCharactersListUseCase: CacheCharactersListUseCase): CachingDataWorkerFactory{
        return CachingDataWorkerFactory(uploadCharacterListUseCase, cacheCharactersListUseCase)
    }


}