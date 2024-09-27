package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun provideMapperForCharacterRepImpl() = CharacterModelMapper()



}