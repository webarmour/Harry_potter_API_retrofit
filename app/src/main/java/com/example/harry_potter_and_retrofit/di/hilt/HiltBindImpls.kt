package com.example.harry_potter_and_retrofit.di.hilt

import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.data.ForumRepositoryImpl
import com.example.harry_potter_and_retrofit.data.paging.repoimpl.CharacterPagingRepositoryImpl
import com.example.harry_potter_and_retrofit.domain.repository.CharacterPagingRepository
import com.example.harry_potter_and_retrofit.domain.repository.CharacterRepository
import com.example.harry_potter_and_retrofit.domain.repository.ForumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HiltBindImpls {

    @Binds
    fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    fun bindForumRepository(
        forumImpl: ForumRepositoryImpl,
    ): ForumRepository

    @Binds
    fun bindPagingRepository(
        forumImpl: CharacterPagingRepositoryImpl,
    ): CharacterPagingRepository


}