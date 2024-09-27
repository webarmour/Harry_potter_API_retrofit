package com.example.harry_potter_and_retrofit.di

import android.app.Application
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import dagger.Module
import dagger.Provides


@Module
class DomainModule() {

    @Provides
    fun provideCharacterRepositoryImpl(
        characterModelMapper: CharacterModelMapper,
        application: Application,
    ): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(mapper = characterModelMapper, application = application)
    }


}