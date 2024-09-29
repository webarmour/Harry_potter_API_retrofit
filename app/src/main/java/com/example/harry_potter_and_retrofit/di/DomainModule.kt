package com.example.harry_potter_and_retrofit.di

import android.app.Application
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.data.localdb.dao.Dao
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import com.example.harry_potter_and_retrofit.domain.usecase.CacheCharactersListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterListUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.GetCharacterUseCase
import com.example.harry_potter_and_retrofit.domain.usecase.UploadCharacterListUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModule() {

    @Provides
    fun provideCharacterRepositoryImpl(
        characterModelMapper: CharacterModelMapper,
        application: Application,
        characterDao: Dao
    ): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(mapper = characterModelMapper, application = application,
            charactersDao = characterDao )
    }

    @Provides
    fun provideGetCharacterListUseCase(characterRepository: CharacterRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(characterRepository)
    }

    @Provides
    fun provideGetCharacterUseCase(characterRepository: CharacterRepository): GetCharacterUseCase {
        return GetCharacterUseCase(characterRepository)
    }

    @Provides
    fun provideUploadListUseCase(characterRepository: CharacterRepository): UploadCharacterListUseCase {
        return UploadCharacterListUseCase(characterRepository)
    }

    @Provides
    fun provideCacheCharacterListUseCase(characterRepository: CharacterRepository): CacheCharactersListUseCase {
        return CacheCharactersListUseCase(characterRepository)
    }


}