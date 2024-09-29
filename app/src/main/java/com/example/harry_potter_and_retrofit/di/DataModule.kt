package com.example.harry_potter_and_retrofit.di

import android.app.Application
import com.example.harry_potter_and_retrofit.data.CharacterRepositoryImpl
import com.example.harry_potter_and_retrofit.data.localdb.dao.Dao
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import com.example.harry_potter_and_retrofit.data.mapper.CharacterModelMapper
import com.example.harry_potter_and_retrofit.data.paging.mapper.CharacterPagingMapper
import com.example.harry_potter_and_retrofit.data.paging.pagingsource.CharacterPagingSource
import com.example.harry_potter_and_retrofit.data.paging.repoimpl.CharacterPagingRepositoryImpl
import dagger.Module
import dagger.Provides

//
//@Module
class DataModule {
//
//    @Provides
//    fun provideMapperForCharacterRepImpl() = CharacterModelMapper()
//
//    @Provides
//    fun provideCharacterRepositoryImpl(
//        characterModelMapper: CharacterModelMapper,
//        characterDao: Dao,
//    ): CharacterRepositoryImpl {
//        return CharacterRepositoryImpl(
//            mapper = characterModelMapper,
//            charactersDao = characterDao
//        )
//    }

//    @Provides
//    fun provideCharacterDao(application: Application):Dao {
//        return CharacterDatabase.getInstance(application).getCharacterDao()
//    }
//
//    @Provides
//    fun provideCharacterPagingMapper():CharacterPagingMapper{
//        return CharacterPagingMapper()
//    }
//
//    @Provides
//    fun provideCharacterPagingSource(pagingMapper: CharacterPagingMapper):CharacterPagingSource{
//        return CharacterPagingSource(pagingMapper)
//    }

//    @Provides
//    fun provideCharacterPagingRepository(pagingSource: CharacterPagingSource):CharacterPagingRepositoryImpl{
//        return CharacterPagingRepositoryImpl(pagingSource)
//    }


}