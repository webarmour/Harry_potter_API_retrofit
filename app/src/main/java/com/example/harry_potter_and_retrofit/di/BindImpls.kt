package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module

@Module
interface BindImpls {

    @Binds
    fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository


}